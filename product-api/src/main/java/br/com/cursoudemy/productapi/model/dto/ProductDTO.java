package br.com.cursoudemy.productapi.model.dto;

import java.util.UUID;

import br.com.cursoudemy.productapi.model.Category;
import br.com.cursoudemy.productapi.model.Supplier;

public class ProductDTO {

  private UUID id;

  private String name;

  private Integer quantityAvailable;

  private Category category;
  
  private Supplier supplier;

  public ProductDTO() {
  }

  public ProductDTO(UUID id, String name, Integer quantityAvailable, Category category, Supplier supplier) {
    this.id = id;
    this.name = name;
    this.quantityAvailable = quantityAvailable;
    this.category = category;
    this.supplier = supplier;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((quantityAvailable == null) ? 0 : quantityAvailable.hashCode());
    result = prime * result + ((category == null) ? 0 : category.hashCode());
    result = prime * result + ((supplier == null) ? 0 : supplier.hashCode());
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
    ProductDTO other = (ProductDTO) obj;
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
    if (quantityAvailable == null) {
      if (other.quantityAvailable != null)
        return false;
    } else if (!quantityAvailable.equals(other.quantityAvailable))
      return false;
    if (category == null) {
      if (other.category != null)
        return false;
    } else if (!category.equals(other.category))
      return false;
    if (supplier == null) {
      if (other.supplier != null)
        return false;
    } else if (!supplier.equals(other.supplier))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "ProductDTO [id=" + id + ", name=" + name + ", quantityAvailable=" + quantityAvailable + ", category="
        + category + ", supplier=" + supplier + "]";
  }

 
}
