package br.com.cursoudemy.productapi.modules.sales.dto;

import br.com.cursoudemy.productapi.modules.sales.enums.SalesStatus;
import java.util.Objects;

public class SalesConfirmationDTO {

    private String salesId;
    private SalesStatus status;
    private String transactionid;

    public SalesConfirmationDTO() {
    }

    public SalesConfirmationDTO(String salesId, SalesStatus status, String transactionid) {
        this.salesId = salesId;
        this.status = status;
        this.transactionid = transactionid;
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

    public String getTransactionid() {
        return this.transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public SalesConfirmationDTO salesId(String salesId) {
        setSalesId(salesId);
        return this;
    }

    public SalesConfirmationDTO status(SalesStatus status) {
        setStatus(status);
        return this;
    }

    public SalesConfirmationDTO transactionid(String transactionid) {
        setTransactionid(transactionid);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SalesConfirmationDTO)) {
            return false;
        }
        SalesConfirmationDTO salesConfirmationDTO = (SalesConfirmationDTO) o;
        return Objects.equals(salesId, salesConfirmationDTO.salesId)
                && Objects.equals(status, salesConfirmationDTO.status)
                && Objects.equals(transactionid, salesConfirmationDTO.transactionid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesId, status, transactionid);
    }

    @Override
    public String toString() {
        return "{" +
                " salesId='" + getSalesId() + "'" +
                ", status='" + getStatus() + "'" +
                ", transactionid='" + getTransactionid() + "'" +
                "}";
    }

}
