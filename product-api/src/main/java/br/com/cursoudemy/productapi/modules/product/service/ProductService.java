package br.com.cursoudemy.productapi.modules.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.exceptions.NotFoundException;
import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.category.service.CategoryService;
import br.com.cursoudemy.productapi.modules.product.mapper.ProductMapper;
import br.com.cursoudemy.productapi.modules.product.model.Product;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductRequest;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductResponse;
import br.com.cursoudemy.productapi.modules.product.repository.ProductRepository;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.service.SupplierService;

@Service
public class ProductService {

  @Autowired
  private ProductMapper productMapper;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private SupplierService supplierService;

  public ProductResponse create(ProductRequest dto) {
    Category category = categoryService.findById(dto.getCategoryId());
    Supplier supplier = supplierService.findById(dto.getSupplierId());

    return productMapper.toDto(productRepository.save(productMapper.toEntity(dto, category, supplier)));
  }

  public List<ProductResponse> findAll() {
    return productMapper.toDto(productRepository.findAll());
  }

  public Product findById(UUID id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("There's no product for the given ID."));

  }

  public ProductResponse findByIdResponse(UUID id) {
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

  
  // List<Product> findByCategoryId(UUID id);
  // List<Product> findBySupplierId(UUID id);

  public List<ProductResponse> findByCategoryId(UUID id) {
    List<Product> listEntites = productRepository
        .findByCategoryId(id);
    return productMapper.toDto(listEntites);
  }

  public List<ProductResponse> findBySupplierId(UUID id) {
    List<Product> listEntites = productRepository
        .findBySupplierId(id);
    return productMapper.toDto(listEntites);
  }

}
