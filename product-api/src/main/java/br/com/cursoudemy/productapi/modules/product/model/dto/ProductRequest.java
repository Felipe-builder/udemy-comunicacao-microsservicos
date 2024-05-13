package br.com.cursoudemy.productapi.modules.product.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductRequest {


  @NotBlank(message = "'name' must be required and does not be empty")
  @Size(min = 3, message = "'name' must be at least 3 characters")
  private String name;

  @NotNull(message = "quantity_available cannot be null")
  @Min(value = 0, message = "quantity_available must be 0 or greater")
  private Integer quantityAvailable;

  @NotNull(message = "category_id cannot be null")
  private Long categoryId;
  
  @NotNull(message = "supplier_id cannot be null")
  private Long supplierId;

  public ProductRequest() {
  }

  public ProductRequest( String name, Integer quantityAvailable, Long categoryId, Long supplierId) {
    this.name = name;
    this.quantityAvailable = quantityAvailable;
    this.categoryId = categoryId;
    this.supplierId = supplierId;
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

  public Long getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Long categoryId) {
    this.categoryId = categoryId;
  }

  public Long getSupplierId() {
    return supplierId;
  }

  public void setSupplierId(Long supplierId) {
    this.supplierId = supplierId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((quantityAvailable == null) ? 0 : quantityAvailable.hashCode());
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
    ProductRequest other = (ProductRequest) obj;
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
    return "CreateProductDTO [name=" + name + ", quantityAvailable=" + quantityAvailable
        + ", categoryId=" + categoryId + ", supplierId=" + supplierId + "]";
  }

  
}

