package br.com.cursoudemy.productapi.modules.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.category.service.CategoryService;
import br.com.cursoudemy.productapi.modules.product.mapper.ProductMapper;
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



}
