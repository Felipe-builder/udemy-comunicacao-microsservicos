package br.com.cursoudemy.productapi.modules.supplier.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.model.dto.SupplierRequest;
import br.com.cursoudemy.productapi.modules.supplier.model.dto.SupplierResponse;

@Component
public class SupplierMapper {
  public SupplierResponse toDto(Supplier entity) {
    return new SupplierResponse(
        entity.getId(),
        entity.getName());
  }

  public Supplier toEntity(SupplierRequest dto) {
    return new Supplier(
        dto.getName());
  }

  public List<SupplierResponse> toDto(List<Supplier> entities) {
    return entities.stream().map(entity -> this.toDto(entity)).collect(Collectors.toList());
  }
}
