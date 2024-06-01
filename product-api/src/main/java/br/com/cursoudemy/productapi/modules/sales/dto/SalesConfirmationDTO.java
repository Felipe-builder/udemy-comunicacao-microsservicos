package br.com.cursoudemy.productapi.modules.sales.dto;


import br.com.cursoudemy.productapi.modules.sales.enums.SalesStatus;
import java.util.Objects;

public class SalesConfirmationDTO {
    
    private String salesId;
    private SalesStatus status;


    public SalesConfirmationDTO() {
    }

    public SalesConfirmationDTO(String salesId, SalesStatus status) {
        this.salesId = salesId;
        this.status = status;
    }

    public String getSalesId() {
        return this.salesId;
    }

    public void setSalesId(String salesId) {
        this.salesId = salesId;
    }

    public SalesStatus getStatus() {
        return this.status;
    }

    public void setStatus(SalesStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SalesConfirmationDTO)) {
            return false;
        }
        SalesConfirmationDTO salesConfirmationDTO = (SalesConfirmationDTO) o;
        return Objects.equals(salesId, salesConfirmationDTO.salesId) && Objects.equals(status, salesConfirmationDTO.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesId, status);
    }

    @Override
    public String toString() {
        return "{" +
            " salesId='" + getSalesId() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
    
}
