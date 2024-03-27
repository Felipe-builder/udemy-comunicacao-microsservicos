package br.com.cursoudemy.productapi.modules.category.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoudemy.productapi.modules.category.model.dto.CategoryRequest;
import br.com.cursoudemy.productapi.modules.category.model.dto.CategoryResponse;
import br.com.cursoudemy.productapi.modules.category.service.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @PostMapping
  public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request) {
    CategoryResponse response = categoryService.create(request);
    return ResponseEntity.created(null).body(response);
  }

  @GetMapping
  public ResponseEntity<List<CategoryResponse>> findAll() {
    List<CategoryResponse> response = categoryService.findAll();
    return ResponseEntity.ok(response);
  }

  @GetMapping("{id}")
  public ResponseEntity<CategoryResponse> findById(@PathVariable UUID id) {
    CategoryResponse response = categoryService.findByIdResponse(id);
    return ResponseEntity.ok(response);
  }

  
  @GetMapping("description/{description}")
  public ResponseEntity<List<CategoryResponse>> findByDescription(@PathVariable String description) {
    List<CategoryResponse> response = categoryService.findByDescription(description);
    return ResponseEntity.ok(response);
  }
}
