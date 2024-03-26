package br.com.cursoudemy.productapi.modules.category.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.exceptions.NotFoundException;
import br.com.cursoudemy.productapi.modules.category.mapper.CategoryMapper;
import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.category.model.dto.CategoryRequest;
import br.com.cursoudemy.productapi.modules.category.model.dto.CategoryResponse;
import br.com.cursoudemy.productapi.modules.category.repository.CategoryRepository;

@Service
public class CategoryService {

  @Autowired
  private CategoryMapper categoryMapper;

  @Autowired
  private CategoryRepository categoryRepository;

  public CategoryResponse create(CategoryRequest dto) {
    Category created = categoryRepository.save(categoryMapper.toEntity(dto));
    return categoryMapper.toDto(created);
  }

  public List<CategoryResponse> findAll() {
    List<Category> categories = categoryRepository.findAll();
    return categoryMapper.toDto(categories);
  }

  public Category findById(UUID categoryId) {
    return categoryRepository
        .findById(categoryId)
        .orElseThrow(() -> new NotFoundException("There's no category for the given ID."));
  }

}
