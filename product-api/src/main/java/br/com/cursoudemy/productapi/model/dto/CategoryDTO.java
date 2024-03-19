package br.com.cursoudemy.productapi.model.dto;

import java.util.UUID;

public class CategoryDTO {
  private UUID id;
  private String description;

  public CategoryDTO() {
  }

  public CategoryDTO(UUID id, String description) {
    this.id = id;
    this.description = description;
  }

  public UUID getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CategoryDTO other = (CategoryDTO) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CategoryDTO [id=" + id + ", description=" + description + "]";
  }

}
