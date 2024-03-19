package br.com.cursoudemy.productapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.cursoudemy.productapi.model.Category;
import br.com.cursoudemy.productapi.model.dto.CategoryDTO;

@Component
public class CategoryMapper {

  public CategoryDTO toDto(Category entity) {
    return new CategoryDTO(
      entity.getId(), entity.getDescription()
    );
  }

  public Category toEntity(CategoryDTO dto) {
    return new Category(
      dto.getId(), dto.getDescription()
    );
  }

  public List<CategoryDTO> toDto(List<Category> entities) {
    return entities.stream().map(entity -> this.toDto(entity)).collect(Collectors.toList());
  }
}
