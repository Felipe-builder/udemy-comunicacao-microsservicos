package br.com.cursoudemy.productapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.mapper.CategoryMapper;
import br.com.cursoudemy.productapi.model.Category;
import br.com.cursoudemy.productapi.model.dto.CategoryRequest;
import br.com.cursoudemy.productapi.model.dto.CategoryResponse;
import br.com.cursoudemy.productapi.repository.CategoryRepository;

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

}
