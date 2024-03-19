package br.com.cursoudemy.productapi.model.dto;

import java.util.UUID;

public class CreateProductDTO {

  private String id;

  private String name;

  private int quantityAvailable;

  private UUID categoryId;
  
  private UUID supplierId;

  public CreateProductDTO() {
  }

  public CreateProductDTO(String id, String name, int quantityAvailable, UUID categoryId, UUID supplierId) {
    this.id = id;
    this.name = name;
    this.quantityAvailable = quantityAvailable;
    this.categoryId = categoryId;
    this.supplierId = supplierId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getQuantityAvailable() {
    return quantityAvailable;
  }

  public void setQuantityAvailable(int quantityAvailable) {
    this.quantityAvailable = quantityAvailable;
  }

  public UUID getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(UUID categoryId) {
    this.categoryId = categoryId;
  }

  public UUID getSupplierId() {
    return supplierId;
  }

  public void setSupplierId(UUID supplierId) {
    this.supplierId = supplierId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + quantityAvailable;
    result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
    result = prime * result + ((supplierId == null) ? 0 : supplierId.hashCode());
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
    CreateProductDTO other = (CreateProductDTO) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (quantityAvailable != other.quantityAvailable)
      return false;
    if (categoryId == null) {
      if (other.categoryId != null)
        return false;
    } else if (!categoryId.equals(other.categoryId))
      return false;
    if (supplierId == null) {
      if (other.supplierId != null)
        return false;
    } else if (!supplierId.equals(other.supplierId))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "CreateProductDTO [id=" + id + ", name=" + name + ", quantityAvailable=" + quantityAvailable
        + ", categoryId=" + categoryId + ", supplierId=" + supplierId + "]";
  }

}

