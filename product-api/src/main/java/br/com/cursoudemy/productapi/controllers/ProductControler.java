package br.com.cursoudemy.productapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoudemy.productapi.model.dto.CreateProductDTO;
import br.com.cursoudemy.productapi.model.dto.ProductDTO;
import br.com.cursoudemy.productapi.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductControler {

  @Autowired
  private ProductService productService;

  @PostMapping()
  public ResponseEntity<ProductDTO> create(@Valid @RequestBody CreateProductDTO dto) {
    ProductDTO response = productService.create(dto);
    return ResponseEntity.created(null).body(response);
  }

  @GetMapping()
  public ResponseEntity<List<ProductDTO>> findAll() {
    List<ProductDTO> response = productService.findAll();
    
    return ResponseEntity.ok(response);
  }
}
