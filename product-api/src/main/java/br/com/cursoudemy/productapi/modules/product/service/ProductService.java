package br.com.cursoudemy.productapi.modules.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.config.handlers.SuccessResponse;
import br.com.cursoudemy.productapi.exceptions.NotFoundException;
import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.category.service.CategoryService;
import br.com.cursoudemy.productapi.modules.product.mapper.ProductMapper;
import br.com.cursoudemy.productapi.modules.product.model.Product;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductRequest;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductResponse;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductStockDTO;
import br.com.cursoudemy.productapi.modules.product.repository.ProductRepository;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.service.SupplierService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProductService {

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

  @Cacheable(value = "products", key = "#id")
  public boolean isSupplierExists(Long id) {
    return productRepository.existsById(id);
  }

  public ProductResponse create(ProductRequest dto) {
    Category category = categoryService.findById(dto.getCategoryId());
    Supplier supplier = supplierService.findById(dto.getSupplierId());

    return productMapper.toDto(productRepository.save(productMapper.toEntity(dto, category, supplier)));
  }

  @Transactional
  public ProductResponse update(ProductRequest request, Long id) {
    if (!isSupplierExists(id)) {
      throw new EntityNotFoundException("Category not found");
    }
    Category category = categoryService.findById(request.getCategoryId());
    Supplier supplier = supplierService.findById(request.getSupplierId());

    Product toUpdate = productMapper.toEntity(request, category, supplier);
    toUpdate.setId(id);

    Product updatedProduct = productRepository.save(toUpdate);

    return productMapper.toDto(updatedProduct);
  }


  public List<ProductResponse> findAll() {
    return productMapper.toDto(productRepository.findAll());
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
    return productMapper.toDto(entity);
  }

  public List<ProductResponse> findByName(String name) {
    List<Product> listEntites = productRepository
        .findByNameIgnoreCaseContaining(name);
    return productMapper.toDto(listEntites);
  }

  public List<ProductResponse> findByCategoryId(Long id) {
    List<Product> listEntites = productRepository
        .findByCategoryId(id);
    return productMapper.toDto(listEntites);
  }

  public List<ProductResponse> findBySupplierId(Long id) {
    List<Product> listEntites = productRepository
        .findBySupplierId(id);
    return productMapper.toDto(listEntites);
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

  public void updateProductStock(ProductStockDTO productStockDTO) {}

}
