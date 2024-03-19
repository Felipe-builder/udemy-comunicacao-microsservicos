package br.com.cursoudemy.productapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cursoudemy.productapi.model.dto.CategoryDTO;
import br.com.cursoudemy.productapi.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @PostMapping
  public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto) {
    CategoryDTO response = categoryService.create(dto);
    return ResponseEntity.created(null).body(response);
  }

  @GetMapping
  public ResponseEntity<List<CategoryDTO>> findAll() {
    List<CategoryDTO> response = categoryService.findAll();
    return ResponseEntity.ok(response);
  }
}
