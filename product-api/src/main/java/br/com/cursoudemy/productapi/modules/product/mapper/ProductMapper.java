package br.com.cursoudemy.productapi.modules.product.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.cursoudemy.productapi.modules.product.model.Product;
import br.com.cursoudemy.productapi.modules.product.model.dto.ProductResponse;

@Component
public class ProductMapper {

  public ProductResponse toDto(Product entity) {
    return new ProductResponse(
      entity.getId(),
      entity.getName(),
      entity.getQuantityAvailable(),
      entity.getCategory(),
      entity.getSupplier()
    );
  }


  public List<ProductResponse> toDto(List<Product> entites) {
    return entites.stream().map(entity -> this.toDto(entity)).collect(Collectors.toList());
  }

}
