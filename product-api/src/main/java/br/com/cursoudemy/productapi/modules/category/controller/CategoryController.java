package br.com.cursoudemy.productapi.modules.category.controller;

import java.util.List;

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
  public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
    CategoryResponse response = categoryService.findByIdResponse(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping("description/{description}")
  public ResponseEntity<List<CategoryResponse>> findByDescription(@PathVariable String description) {
    List<CategoryResponse> response = categoryService.findByDescription(description);
    return ResponseEntity.ok(response);
  }

  @PutMapping("{id}")
  public ResponseEntity<CategoryResponse> update(@Valid @RequestBody CategoryRequest request, @PathVariable Long id) {
    return ResponseEntity.ok(categoryService.update(request, id));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<SuccessResponse> delete(@PathVariable Long id) {
    return ResponseEntity.ok(categoryService.delete(id));
  }

}
