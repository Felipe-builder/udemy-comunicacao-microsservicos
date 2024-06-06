package br.com.cursoudemy.productapi.modules.product.model.dto;

import java.util.List;
import java.util.Objects;

public class ProductStockDTO {

    private String salesId;
    private List<ProductQuantityDTO> products;
    private String transactionid;

    public ProductStockDTO() {
    }

    public ProductStockDTO(String salesId, List<ProductQuantityDTO> products, String transactionid) {
        this.salesId = salesId;
        this.products = products;
        this.transactionid = transactionid;
    }

    public String getSalesId() {
        return this.salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public List<ProductQuantityDTO> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductQuantityDTO> products) {
        this.products = products;
    }

    public String getTransactionid() {
        return this.transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public ProductStockDTO salesId(String salesId) {
        setSalesId(salesId);
        return this;
    }

    public ProductStockDTO products(List<ProductQuantityDTO> products) {
        setProducts(products);
        return this;
    }

    public ProductStockDTO transactionid(String transactionid) {
        setTransactionid(transactionid);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductStockDTO)) {
            return false;
        }
        ProductStockDTO productStockDTO = (ProductStockDTO) o;
        return Objects.equals(salesId, productStockDTO.salesId) && Objects.equals(products, productStockDTO.products)
                && Objects.equals(transactionid, productStockDTO.transactionid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesId, products, transactionid);
    }

    @Override
    public String toString() {
        return "{" +
                " salesId='" + getSalesId() + "'" +
                ", products='" + getProducts() + "'" +
                ", transactionid='" + getTransactionid() + "'" +
                "}";
    }

}
