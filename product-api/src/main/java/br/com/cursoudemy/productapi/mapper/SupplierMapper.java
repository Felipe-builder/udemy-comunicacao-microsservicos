package br.com.cursoudemy.productapi.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.cursoudemy.productapi.model.Supplier;
import br.com.cursoudemy.productapi.model.dto.SupplierDTO;

@Component
public class SupplierMapper {
  public SupplierDTO toDto(Supplier entity) {
    return new SupplierDTO(
        entity.getId(),
        entity.getName());
  }

  public Supplier toEntity(SupplierDTO dto) {
    return new Supplier(
        dto.getId(),
        dto.getName());
  }

  public List<SupplierDTO> toDto(List<Supplier> entities) {
    return entities.stream().map(entity -> this.toDto(entity)).collect(Collectors.toList());
  }
}
