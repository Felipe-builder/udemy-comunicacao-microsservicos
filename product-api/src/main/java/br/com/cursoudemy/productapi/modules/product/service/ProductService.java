package br.com.cursoudemy.productapi.modules.product.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.exceptions.NotFoundException;
import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.category.repository.CategoryRepository;
import br.com.cursoudemy.productapi.modules.product.mapper.ProductMapper;
import br.com.cursoudemy.productapi.modules.product.model.Product;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductRequest;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductResponse;
import br.com.cursoudemy.productapi.modules.product.repository.ProductRepository;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.repository.SupplierRepository;

@Service
public class ProductService {

  @Autowired
  private ProductMapper productMapper;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private SupplierRepository supplierRepository;

  public ProductResponse create(ProductRequest dto) {
    Category category = categoryRepository.findById(dto.getCategoryId())
        .orElseThrow(() -> new NotFoundException("Category not Found"));
    Supplier supplier = supplierRepository.findById(dto.getSupplierId())
        .orElseThrow(() -> new NotFoundException("Supplier not Found"));

    Product entity = new Product(UUID.randomUUID(), dto.getName(), dto.getQuantityAvailable(), category, supplier);
    
    return productMapper.toDto(productRepository.save(entity));
  }

  public List<ProductResponse> findAll() {
    return productMapper.toDto(productRepository.findAll());
  }



}
