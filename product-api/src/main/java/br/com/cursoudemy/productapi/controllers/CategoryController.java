package br.com.cursoudemy.productapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoudemy.productapi.model.dto.CategoryRequest;
import br.com.cursoudemy.productapi.model.dto.CategoryResponse;
import br.com.cursoudemy.productapi.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @PostMapping
  public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request) {
    CategoryResponse response = categoryService.create(request);
    return ResponseEntity.created(null).body(response);
  }

  @GetMapping
  public ResponseEntity<List<CategoryResponse>> findAll() {
    List<CategoryResponse> response = categoryService.findAll();
    return ResponseEntity.ok(response);
  }
}
