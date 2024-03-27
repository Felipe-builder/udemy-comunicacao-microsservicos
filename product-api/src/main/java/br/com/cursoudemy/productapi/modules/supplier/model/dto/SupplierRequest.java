package br.com.cursoudemy.productapi.modules.supplier.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SupplierRequest {
  @NotBlank(message = "'name' must be required and does not be empty")
  @Size(min = 3, message = "'name' must be at least 3 characters")
  private String name;

  public SupplierRequest() {
  }

  public SupplierRequest(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
    SupplierRequest other = (SupplierRequest) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "{" +
        " name='" + getName() + "'" +
        "}";
  }

}
