package br.com.cursoudemy.productapi.modules.product.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.category.model.dto.CategoryResponse;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.model.dto.SupplierResponse;
import java.util.Objects;

public class ProductSalesResponse {

    private Long id;

    private String name;

    @JsonProperty("quantity_available")
    private Integer quantityAvailable;

    private CategoryResponse category;
    
    private SupplierResponse supplier;

    private List<String> sales;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;



    public ProductSalesResponse() {
    }

    public ProductSalesResponse(Long id, String name, Integer quantityAvailable, CategoryResponse category, SupplierResponse supplier, List<String> sales, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.quantityAvailable = quantityAvailable;
        this.category = category;
        this.supplier = supplier;
        this.sales = sales;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantityAvailable() {
        return this.quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public SupplierResponse getSupplier() {
        return this.supplier;
    }

    public void setSupplier(SupplierResponse supplier) {
        this.supplier = supplier;
    }

    public CategoryResponse getCategory() {
        return this.category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }

    public List<String> getSales() {
        return this.sales;
    }

    public void setSales(List<String> sales) {
        this.sales = sales;
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
        if (!(o instanceof ProductSalesResponse)) {
            return false;
        }
        ProductSalesResponse productSalesResponse = (ProductSalesResponse) o;
        return Objects.equals(id, productSalesResponse.id) && Objects.equals(name, productSalesResponse.name) && Objects.equals(quantityAvailable, productSalesResponse.quantityAvailable) && Objects.equals(supplier, productSalesResponse.supplier) && Objects.equals(category, productSalesResponse.category) && Objects.equals(sales, productSalesResponse.sales) && Objects.equals(createdAt, productSalesResponse.createdAt) && Objects.equals(updatedAt, productSalesResponse.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantityAvailable, supplier, category, sales, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", quantityAvailable='" + getQuantityAvailable() + "'" +
            ", supplier='" + getSupplier() + "'" +
            ", category='" + getCategory() + "'" +
            ", sales='" + getSales() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            "}";
    }
    
}
