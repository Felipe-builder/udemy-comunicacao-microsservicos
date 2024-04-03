package br.com.cursoudemy.productapi.modules.product.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoudemy.productapi.config.handlers.SuccessResponse;
import br.com.cursoudemy.productapi.modules.category.model.dto.CategoryRequest;
import br.com.cursoudemy.productapi.modules.category.model.dto.CategoryResponse;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductRequest;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductResponse;
import br.com.cursoudemy.productapi.modules.product.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductControler {

  @Autowired
  private ProductService productService;

  @PostMapping()
  public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
    ProductResponse response = productService.create(request);
    return ResponseEntity.created(null).body(response);
  }

  @GetMapping()
  public ResponseEntity<List<ProductResponse>> findAll() {
    List<ProductResponse> response = productService.findAll();
    return ResponseEntity.ok(response);
  }

  @GetMapping("{id}")
  public ResponseEntity<ProductResponse> findById(@PathVariable UUID id) {
    ProductResponse response = productService.findByIdResponse(id);
    return ResponseEntity.ok(response);
  }

  
  @GetMapping("name/{name}")
  public ResponseEntity<List<ProductResponse>> findByName(@PathVariable String name) {
    List<ProductResponse> response = productService.findByName(name);
    return ResponseEntity.ok(response);
  }

  @GetMapping("category/{categoryId}")
  public ResponseEntity<List<ProductResponse>> findByCategoryId(@PathVariable UUID categoryId) {
    List<ProductResponse> response = productService.findByCategoryId(categoryId);
    return ResponseEntity.ok(response);
  }
  
  @GetMapping("supplier/{supplierId}")
  public ResponseEntity<List<ProductResponse>> findBySupplierId(@PathVariable UUID supplierId) {
    List<ProductResponse> response = productService.findBySupplierId(supplierId);
    return ResponseEntity.ok(response);
  }

  @PutMapping("{id}")
  public ResponseEntity<ProductResponse> update(@Valid @RequestBody ProductRequest request, @PathVariable UUID id) {
    return ResponseEntity.ok(productService.update(request, id));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<SuccessResponse> delete(@PathVariable UUID id) {
    return ResponseEntity.ok(productService.delete(id));
  }
}
