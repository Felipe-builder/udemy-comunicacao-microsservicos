package br.com.cursoudemy.productapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import br.com.cursoudemy.productapi.mapper.ProductMapper;
import br.com.cursoudemy.productapi.model.Category;
import br.com.cursoudemy.productapi.model.Product;
import br.com.cursoudemy.productapi.model.Supplier;
import br.com.cursoudemy.productapi.model.dto.CreateProductDTO;
import br.com.cursoudemy.productapi.model.dto.ProductDTO;
import br.com.cursoudemy.productapi.repository.CategoryRepository;
import br.com.cursoudemy.productapi.repository.ProductRepository;
import br.com.cursoudemy.productapi.repository.SupplierRepository;

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

  public ProductDTO create(CreateProductDTO dto) {

    Category category = categoryRepository.findById(dto.getCategoryId())
        .orElseThrow(() -> new RuntimeException("Category not Found"));
    Supplier supplier = supplierRepository.findById(dto.getSupplierId())
        .orElseThrow(() -> new RuntimeException("Supplier not Found"));

    Product entity = new Product(dto.getName(), dto.getQuantityAvailable(), category, supplier);
    
    return productMapper.toDto(productRepository.save(entity));
  }

}
