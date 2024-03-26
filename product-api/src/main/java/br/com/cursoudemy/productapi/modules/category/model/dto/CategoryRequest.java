package br.com.cursoudemy.productapi.modules.category.model.dto;


public class CategoryRequest {
  private String description;
  

  public CategoryRequest() {
  }

  public CategoryRequest(String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
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
    CategoryRequest other = (CategoryRequest) obj;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "{" +
      " description='" + getDescription() + "'" +
      "}";
  }
  
}
