package br.com.cursoudemy.productapi.modules.product.model.dto;

import java.util.List;

public class ProductCheckStockRequest {
    
    List<ProductQuantityDTO> products;

    public ProductCheckStockRequest() {
    }

    public ProductCheckStockRequest(List<ProductQuantityDTO> products) {
        this.products = products;
    }

    public List<ProductQuantityDTO> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductQuantityDTO> products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((products == null) ? 0 : products.hashCode());
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
        ProductCheckStockRequest other = (ProductCheckStockRequest) obj;
        if (products == null) {
            if (other.products != null)
                return false;
        } else if (!products.equals(other.products))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "{" +
            " products='" + getProducts() + "'" +
            "}";
    }
    
}
