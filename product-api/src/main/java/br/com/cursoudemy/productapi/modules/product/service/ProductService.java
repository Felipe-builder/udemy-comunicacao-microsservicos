package br.com.cursoudemy.productapi.modules.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static br.com.cursoudemy.productapi.config.RequestUtil.getCurrenRequest;
import static org.springframework.util.ObjectUtils.isEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cursoudemy.productapi.config.handlers.SuccessResponse;
import br.com.cursoudemy.productapi.exceptions.NotFoundException;
import br.com.cursoudemy.productapi.exceptions.ValidationException;
import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.category.service.CategoryService;
import br.com.cursoudemy.productapi.modules.product.mapper.ProductMapper;
import br.com.cursoudemy.productapi.modules.product.model.Product;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductCheckStockRequest;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductQuantityDTO;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductRequest;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductResponse;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductSalesResponse;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductStockDTO;
import br.com.cursoudemy.productapi.modules.product.repository.ProductRepository;
import br.com.cursoudemy.productapi.modules.sales.client.SalesClient;
import br.com.cursoudemy.productapi.modules.sales.dto.SalesConfirmationDTO;
import br.com.cursoudemy.productapi.modules.sales.dto.SalesProductResponse;
import br.com.cursoudemy.productapi.modules.sales.enums.SalesStatus;
import br.com.cursoudemy.productapi.modules.sales.rabbitmq.SalesConfirmationSender;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.service.SupplierService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProductService {

  private static final Logger logger = Logger.getLogger(ProductService.class.getName());

  @Autowired
  private ProductMapper productMapper;

  @Autowired
  private ProductRepository productRepository;

  @Lazy
  @Autowired
  private CategoryService categoryService;

  @Lazy
  @Autowired
  private SupplierService supplierService;

  @Autowired
  private SalesConfirmationSender salesConfirmationSender;

  @Autowired
  private SalesClient salesClient;

  @Cacheable(value = "products", key = "#id")
  public boolean isSupplierExists(Long id) {
    return productRepository.existsById(id);
  }

  public ProductResponse create(ProductRequest dto) {
    Category category = categoryService.findById(dto.getCategoryId());
    Supplier supplier = supplierService.findById(dto.getSupplierId());

    return productMapper.toResponse(productRepository.save(productMapper.toEntity(dto, category, supplier)));
  }

  @Transactional
  public ProductResponse update(ProductRequest request, Long id) {
    if (!isSupplierExists(id)) {
      throw new EntityNotFoundException("Product not found");
    }
    Category category = categoryService.findById(request.getCategoryId());
    Supplier supplier = supplierService.findById(request.getSupplierId());

    Product toUpdate = productMapper.toEntity(request, category, supplier);
    toUpdate.setId(id);

    Product updatedProduct = productRepository.save(toUpdate);

    return productMapper.toResponse(updatedProduct);
  }

  public List<ProductResponse> findAll() {
    return productMapper.toResponse(productRepository.findAll());
  }

  public Product findById(Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("There's no product for the given ID."));

  }

  public ProductResponse findByIdResponse(Long id) {
    Product entity = productRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("There's no product for the given ID."));
    return productMapper.toResponse(entity);
  }

  public List<ProductResponse> findByName(String name) {
    List<Product> listEntites = productRepository
        .findByNameIgnoreCaseContaining(name);
    return productMapper.toResponse(listEntites);
  }

  public List<ProductResponse> findByCategoryId(Long id) {
    List<Product> listEntites = productRepository
        .findByCategoryId(id);
    return productMapper.toResponse(listEntites);
  }

  public List<ProductResponse> findBySupplierId(Long id) {
    List<Product> listEntites = productRepository
        .findBySupplierId(id);
    return productMapper.toResponse(listEntites);
  }

  public Boolean existsByCategoryId(Long id) {
    return productRepository.existsByCategoryId(id);
  }

  public Boolean existsBySupplierId(Long id) {
    return productRepository.existsBySupplierId(id);
  }

  public SuccessResponse delete(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("ID is required");
    }

    productRepository.deleteById(id);
    return SuccessResponse.create("The product was deleted.");
  }

  public void updateProductStock(ProductStockDTO productStockDTO) {
    try {
      validateStockUpdateData(productStockDTO);
      updateStock(productStockDTO);
    } catch (Exception e) {
      logger.info("Error while try to update stock for message with error: {}\n" + e.getMessage());
      salesConfirmationSender
          .sendSalesConfirmationMessage(
              new SalesConfirmationDTO(productStockDTO.getSalesId(), SalesStatus.REJECTED,
                  productStockDTO.getTransactionid()));
    }
  }

  public ProductSalesResponse findProductsSales(Long id) {
    var product = findById(id);
    try {
      var sales = getSalesByProductId(product.getId());
      return productMapper.toProductSalesResponse(product, sales.getSalesIds());
    } catch (Exception e) {
      logger.info("Exception thrown by instance of: " + e.getClass().getName());
      logger.info("There was an error trying to get the product's sales: {}\n" + e.getMessage());
      throw e;
    }
  }

  public SuccessResponse checkProductStock(ProductCheckStockRequest request) {
    try {
      var currentRequest = getCurrenRequest();
      var transactionid = currentRequest.getHeader("transactionid");
      var serviceid = currentRequest.getAttribute("serviceid");
      logger.info(String.format(
          "Request POST product stock with data %s | [transactionID: %s | serviceID: %s]",
          new ObjectMapper().writeValueAsString(request),
          transactionid,
          serviceid));

      if (isEmpty(request) || isEmpty(request.getProducts())) {
        throw new ValidationException("The request data and products must be informed");
      }

      request
          .getProducts()
          .forEach(this::validateStock);
      var response = SuccessResponse.create("The stock is ok!");
      logger.info(String.format(
          "Response POST product stock with data %s | [transactionID: %s | serviceID: %s]",
          new ObjectMapper().writeValueAsString(response),
          transactionid,
          serviceid));
      return response;
    } catch (Exception e) {
      throw new ValidationException(e.getMessage());
    }
  }

  private void validateStock(ProductQuantityDTO productQuantityDTO) {
    if (isEmpty(productQuantityDTO.getProductId()) || isEmpty(productQuantityDTO.getQuantity())) {
      throw new ValidationException("Product ID and quantity must be informed.");
    }
    var product = findById(productQuantityDTO.getProductId());
    if (productQuantityDTO.getQuantity() > product.getQuantityAvailable()) {
      throw new ValidationException(String.format("The product ID %s is out of stock.", product.getId()));
    }
  }

  private void updateStock(ProductStockDTO productStockDTO) {
    var productsForUpdate = new ArrayList<Product>();

    productStockDTO
        .getProducts()
        .forEach(salesProduct -> {
          Product existingProduct = findById(salesProduct.getProductId());
          validateQuantyInStock(salesProduct, existingProduct);
          existingProduct.updateStock(salesProduct.getQuantity());
          productsForUpdate.add(existingProduct);
        });
    if (!isEmpty(productsForUpdate)) {
      productRepository.saveAll(productsForUpdate);
      SalesConfirmationDTO approvedMessage = new SalesConfirmationDTO(productStockDTO.getSalesId(),
          SalesStatus.APPROVED, productStockDTO.getTransactionid());
      salesConfirmationSender.sendSalesConfirmationMessage(approvedMessage);
    }
  }

  @Transactional
  private void validateStockUpdateData(ProductStockDTO productStockDTO) {

    if (isEmpty(productStockDTO)
        || isEmpty(productStockDTO.getSalesId())) {
      throw new ValidationException("The product data and the sales ID must be informed.");
    }

    if (isEmpty(productStockDTO.getProducts())) {
      throw new ValidationException("The sales products must be informed.");
    }

    productStockDTO
        .getProducts()
        .forEach(salesProduct -> {
          if (isEmpty(salesProduct.getQuantity())
              || isEmpty(salesProduct.getProductId())) {
            throw new ValidationException("The sales products must be informed.");
          }

        });
  }

  private void validateQuantyInStock(ProductQuantityDTO salesProduct, Product existingProduct) {
    if (salesProduct.getQuantity() > existingProduct.getQuantityAvailable()) {
      throw new ValidationException("The product id is out of stock.\n" + existingProduct.getId());
    }
  }

  private SalesProductResponse getSalesByProductId(Long productId) {
    try {
      var currentRequest = getCurrenRequest();
      var transactionid = currentRequest.getHeader("transactionid");
      var serviceid = currentRequest.getHeader("serviceid");

      logger.info(String.format(
          "Sending GET request to orders by productId %s | [transactionID: %s | serviceID: %s]",
          productId,
          transactionid,
          serviceid));
      var response = salesClient
          .findSalesByProductId(productId)
          .orElseThrow(() -> new ValidationException("The sales was not found by this product"));
      logger.info(String.format(
            "Recieving response from orders by productId with data %s | [transactionID: %s | serviceID: %s]",
            new ObjectMapper().writeValueAsString(response),
            transactionid,
            serviceid));
      return response;
    } catch (Exception e) {
      throw new ValidationException("The sales could not be found.");
    }
  }

}
