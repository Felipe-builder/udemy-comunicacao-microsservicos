package br.com.cursoudemy.productapi.modules.sales.dto;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

public class SalesProductResponse {
    
    private List<UUID> salesIds;

    public SalesProductResponse() {
    }

    public SalesProductResponse(List<UUID> salesIds) {
        this.salesIds = salesIds;
    }

    public List<UUID> getSalesIds() {
        return this.salesIds;
    }

    public void setSalesIds(List<UUID> salesIds) {
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
