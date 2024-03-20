package br.com.cursoudemy.productapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.cursoudemy.productapi.model.Category;
import br.com.cursoudemy.productapi.model.dto.CategoryRequest;
import br.com.cursoudemy.productapi.model.dto.CategoryResponse;

@Component
public class CategoryMapper {

  public CategoryResponse toDto(Category entity) {
    return new CategoryResponse(
      entity.getId(), entity.getDescription()
    );
  }

  public Category toEntity(CategoryRequest dto) {
    return new Category(
      dto.getDescription()
    );
  }

  public List<CategoryResponse> toDto(List<Category> entities) {
    return entities.stream().map(entity -> this.toDto(entity)).collect(Collectors.toList());
  }
}
