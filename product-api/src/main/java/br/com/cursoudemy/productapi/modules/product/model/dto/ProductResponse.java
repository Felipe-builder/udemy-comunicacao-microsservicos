package br.com.cursoudemy.productapi.modules.product.model.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import java.util.Objects;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductResponse {

  private UUID id;

  private String name;

  private Integer quantityAvailable;

  private Category category;

  private Supplier supplier;

  @JsonProperty("created_at")
  private LocalDateTime createdAt;

  @JsonProperty("updated_at")
  private LocalDateTime updatedAt;

  public ProductResponse() {
  }

  public ProductResponse(UUID id, String name, Integer quantityAvailable, Category category, Supplier supplier,
      LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.name = name;
    this.quantityAvailable = quantityAvailable;
    this.category = category;
    this.supplier = supplier;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
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

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public void setSupplier(Supplier supplier) {
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
