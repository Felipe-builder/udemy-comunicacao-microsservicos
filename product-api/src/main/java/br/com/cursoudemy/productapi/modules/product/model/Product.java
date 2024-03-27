package br.com.cursoudemy.productapi.modules.product.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "quantity_available", nullable = false)
  private Integer quantityAvailable;

  @ManyToOne
  @JoinColumn(name = "fk_category", nullable = false)
  private Category category;

  @ManyToOne
  @JoinColumn(name = "fk_supplier", nullable = false)
  private Supplier supplier;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public Product() {
  }

  public Product( String name, Integer quantityAvailable, Category category, Supplier supplier) {
    this.name = name;
    this.quantityAvailable = quantityAvailable;
    this.category = category;
    this.supplier = supplier;
  }

  public Product(UUID id, String name, Integer quantityAvailable, Category category, Supplier supplier) {
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

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
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
    Product other = (Product) obj;
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
    return "Product [id=" + id + ", name=" + name + ", quantityAvailable=" + quantityAvailable + ", category="
        + category + ", supplier=" + supplier + "]";
  }

}
