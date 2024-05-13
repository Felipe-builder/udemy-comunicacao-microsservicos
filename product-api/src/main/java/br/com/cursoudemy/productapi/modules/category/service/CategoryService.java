package br.com.cursoudemy.productapi.modules.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.config.handlers.SuccessResponse;
import br.com.cursoudemy.productapi.exceptions.NotFoundException;
import br.com.cursoudemy.productapi.exceptions.ValidationException;
import br.com.cursoudemy.productapi.modules.category.mapper.CategoryMapper;
import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.category.model.dto.CategoryRequest;
import br.com.cursoudemy.productapi.modules.category.model.dto.CategoryResponse;
import br.com.cursoudemy.productapi.modules.category.repository.CategoryRepository;
import br.com.cursoudemy.productapi.modules.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CategoryService {

  @Autowired
  private CategoryMapper categoryMapper;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ProductService productService;

  @Cacheable(value = "categories", key = "#id")
  public boolean isSupplierExists(Long id) {
    return categoryRepository.existsById(id);
  }

  public CategoryResponse create(CategoryRequest request) {
    Category created = categoryRepository.save(categoryMapper.toEntity(request));
    return categoryMapper.toDto(created);
  }

  @Transactional
  public CategoryResponse update(CategoryRequest request, Long id) {
    if (!isSupplierExists(id)) {
      throw new EntityNotFoundException("Category not found");
    }

    Category toUpdate = categoryMapper.toEntity(request);
    toUpdate.setId(id);
    Category updatedCategory = categoryRepository.save(toUpdate);

    return categoryMapper.toDto(updatedCategory);
  }

  public List<CategoryResponse> findAll() {
    List<Category> categories = categoryRepository.findAll();
    return categoryMapper.toDto(categories);
  }

  public Category findById(Long categoryId) {
    return categoryRepository
        .findById(categoryId)
        .orElseThrow(() -> new NotFoundException("There's no category for the given ID."));
  }

  public CategoryResponse findByIdResponse(Long categoryId) {
    Category entity = categoryRepository
        .findById(categoryId)
        .orElseThrow(() -> new NotFoundException("There's no category for the given ID."));
    return categoryMapper.toDto(entity);
  }

  public List<CategoryResponse> findByDescription(String description) {
    List<Category> listEntites = categoryRepository
        .findByDescriptionIgnoreCaseContaining(description);
    return categoryMapper.toDto(listEntites);
  }

  public SuccessResponse delete(Long id) {
    if (id == null) {
      throw new IllegalArgumentException("ID is required");
    }
    if (productService.existsByCategoryId(id)) {
      throw new ValidationException("You cannot delete this category because it's already defined byh a product");
    }

    categoryRepository.deleteById(id);
    return SuccessResponse.create("The category was deleted.");
  }
}
