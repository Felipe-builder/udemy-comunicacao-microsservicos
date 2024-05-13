package br.com.cursoudemy.productapi.modules.product.model.dto;

import java.util.Objects;

public class ProductQuantityDTO {

    private Long productId;
    private Integer quantity;

    public ProductQuantityDTO() {
    }

    public ProductQuantityDTO(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductQuantityDTO)) {
            return false;
        }
        ProductQuantityDTO productQuantityDTO = (ProductQuantityDTO) o;
        return Objects.equals(productId, productQuantityDTO.productId)
                && Objects.equals(quantity, productQuantityDTO.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }

    @Override
    public String toString() {
        return "{" +
                " productId='" + getProductId() + "'" +
                ", quantity='" + getQuantity() + "'" +
                "}";
    }

}
