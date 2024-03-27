package br.com.cursoudemy.productapi.modules.category.model.dto;

import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CategoryResponse {
  private UUID id;  
  private String description;

  public CategoryResponse() {
  }

  public CategoryResponse(UUID id, String description) {
    this.id = id;
    this.description = description;
  }

  public UUID getId() {
    return this.id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public CategoryResponse id(UUID id) {
    setId(id);
    return this;
  }

  public CategoryResponse description(String description) {
    setDescription(description);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CategoryResponse)) {
            return false;
        }
        CategoryResponse categoryResponse = (CategoryResponse) o;
        return Objects.equals(id, categoryResponse.id) && Objects.equals(description, categoryResponse.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", description='" + getDescription() + "'" +
      "}";
  }
  
}
