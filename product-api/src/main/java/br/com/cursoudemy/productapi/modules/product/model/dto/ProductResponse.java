package br.com.cursoudemy.productapi.modules.product.model.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.cursoudemy.productapi.modules.category.model.dto.CategoryResponse;
import br.com.cursoudemy.productapi.modules.supplier.model.dto.SupplierResponse;

import java.util.Objects;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductResponse {

  private Long id;

  private String name;

  private Integer quantityAvailable;

  private SupplierResponse supplier;
  
  private CategoryResponse category;

  @JsonProperty("created_at")
  private LocalDateTime createdAt;

  @JsonProperty("updated_at")
  private LocalDateTime updatedAt;

  public ProductResponse() {
  }

  public ProductResponse(Long id, String name, Integer quantityAvailable, CategoryResponse category, SupplierResponse supplier,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.name = name;
    this.quantityAvailable = quantityAvailable;
    this.category = category;
    this.supplier = supplier;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getQuantityAvailable() {
    return quantityAvailable;
  }

  public void setQuantityAvailable(Integer quantityAvailable) {
    this.quantityAvailable = quantityAvailable;
  }

  public CategoryResponse getCategory() {
    return category;
  }

  public void setCategory(CategoryResponse category) {
    this.category = category;
  }

  public SupplierResponse getSupplier() {
    return supplier;
  }

  public void setSupplier(SupplierResponse supplier) {
    this.supplier = supplier;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof ProductResponse)) {
      return false;
    }
    ProductResponse productResponse = (ProductResponse) o;
    return Objects.equals(id, productResponse.id) && Objects.equals(name, productResponse.name)
        && Objects.equals(quantityAvailable, productResponse.quantityAvailable)
        && Objects.equals(category, productResponse.category) && Objects.equals(supplier, productResponse.supplier)
        && Objects.equals(createdAt, productResponse.createdAt) && Objects.equals(updatedAt, productResponse.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, quantityAvailable, category, supplier, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    return "{" +
        " id='" + getId() + "'" +
        ", name='" + getName() + "'" +
        ", quantityAvailable='" + getQuantityAvailable() + "'" +
        ", category='" + getCategory() + "'" +
        ", supplier='" + getSupplier() + "'" +
        ", createdAt='" + getCreatedAt() + "'" +
        ", updatedAt='" + getUpdatedAt() + "'" +
        "}";
  }

}
