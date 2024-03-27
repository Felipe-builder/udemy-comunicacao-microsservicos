package br.com.cursoudemy.productapi.modules.supplier.model.dto;

import java.util.UUID;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Objects;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SupplierResponse {
  private UUID id;
  private String name;

  public SupplierResponse() {
  }

  public SupplierResponse(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  public UUID getId() {
    return this.id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof SupplierResponse)) {
      return false;
    }
    SupplierResponse categoryResponse = (SupplierResponse) o;
    return Objects.equals(id, categoryResponse.id) && Objects.equals(name, categoryResponse.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "{" +
        " id='" + getId() + "'" +
        ", name='" + getName() + "'" +
        "}";
  }

}
