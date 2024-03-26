package br.com.cursoudemy.productapi.modules.product.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest dto) {
    ProductResponse response = productService.create(dto);
    return ResponseEntity.created(null).body(response);
  }

  @GetMapping()
  public ResponseEntity<List<ProductResponse>> findAll() {
    List<ProductResponse> response = productService.findAll();

    return ResponseEntity.ok(response);
  }
}
