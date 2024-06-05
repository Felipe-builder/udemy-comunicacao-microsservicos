package br.com.cursoudemy.productapi.modules.sales.dto;

import java.util.List;

public class SalesProductResponse {
    
    private List<String> salesIds;

    public SalesProductResponse() {
    }

    public SalesProductResponse(List<String> salesIds) {
        this.salesIds = salesIds;
    }

    public List<String> getSalesIds() {
        return this.salesIds;
    }

    public void setSalesIds(List<String> salesIds) {
        this.salesIds = salesIds;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((salesIds == null) ? 0 : salesIds.hashCode());
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
        SalesProductResponse other = (SalesProductResponse) obj;
        if (salesIds == null) {
            if (other.salesIds != null)
                return false;
        } else if (!salesIds.equals(other.salesIds))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "{" +
            " salesIds='" + getSalesIds() + "'" +
            "}";
    }
    
}
