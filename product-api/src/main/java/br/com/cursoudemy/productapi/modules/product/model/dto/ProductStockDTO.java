package br.com.cursoudemy.productapi.modules.product.model.dto;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

public class ProductStockDTO {

    private UUID salesId;
    private List<ProductQuantityDTO> products;

    public ProductStockDTO() {
    }

    public ProductStockDTO(UUID salesId, List<ProductQuantityDTO> products) {
        this.salesId = salesId;
        this.products = products;
    }

    public UUID getSalesId() {
        return this.salesId;
    }

    public void setSalesId(UUID salesId) {
        this.salesId = salesId;
    }

    public List<ProductQuantityDTO> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductQuantityDTO> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductStockDTO)) {
            return false;
        }
        ProductStockDTO productStockDTO = (ProductStockDTO) o;
        return Objects.equals(salesId, productStockDTO.salesId) && Objects.equals(products, productStockDTO.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesId, products);
    }

    @Override
    public String toString() {
        return "{" +
                " salesId='" + getSalesId() + "'" +
                ", products='" + getProducts() + "'" +
                "}";
    }

}
