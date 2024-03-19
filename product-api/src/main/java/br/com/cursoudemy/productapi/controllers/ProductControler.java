package br.com.cursoudemy.productapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoudemy.productapi.model.dto.CreateProductDTO;
import br.com.cursoudemy.productapi.model.dto.ProductDTO;
import br.com.cursoudemy.productapi.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductControler {

  @Autowired
  private ProductService productService;

  @PostMapping()
  public ResponseEntity<ProductDTO> create(@RequestBody CreateProductDTO dto) {

    ProductDTO createdDto = productService.create(dto);
    return ResponseEntity.created(null).body(createdDto);
  }
}
