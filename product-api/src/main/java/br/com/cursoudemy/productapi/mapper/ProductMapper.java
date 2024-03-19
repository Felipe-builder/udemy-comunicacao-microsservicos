package br.com.cursoudemy.productapi.mapper;

import org.springframework.stereotype.Component;

import br.com.cursoudemy.productapi.model.Product;
import br.com.cursoudemy.productapi.model.dto.ProductDTO;

@Component
public class ProductMapper {

  public ProductDTO toDto(Product entity) {
    return new ProductDTO(
      entity.getId(),
      entity.getName(),
      entity.getQuantityAvailable(),
      entity.getCategory(),
      entity.getSupplier()
    );
  }

}
