package br.com.cursoudemy.productapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.mapper.CategoryMapper;
import br.com.cursoudemy.productapi.model.Category;
import br.com.cursoudemy.productapi.model.dto.CategoryDTO;
import br.com.cursoudemy.productapi.repository.CategoryRepository;

@Service
public class CategoryService {

  @Autowired
  private CategoryMapper categoryMapper;

  @Autowired
  private CategoryRepository categoryRepository;

  public CategoryDTO create(CategoryDTO dto) {
    Category created = categoryRepository.save(categoryMapper.toEntity(dto));
    return categoryMapper.toDto(created);
  }

  public List<CategoryDTO> findAll() {
    List<Category> categories = categoryRepository.findAll();
    return categoryMapper.toDto(categories);
  } 

}
