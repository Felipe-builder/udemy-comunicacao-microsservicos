package br.com.cursoudemy.productapi.config.handlers;

import java.util.Objects;

import org.springframework.http.HttpStatus;

public class SuccessResponse {

    private Integer status;
    private String message;

    public SuccessResponse() {
    }

    public SuccessResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static SuccessResponse create(String message) {
        return new SuccessResponse(HttpStatus.OK.value(), message);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SuccessResponse)) {
            return false;
        }
        SuccessResponse successResponse = (SuccessResponse) o;
        return Objects.equals(status, successResponse.status) && Objects.equals(message, successResponse.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }

    @Override
    public String toString() {
        return "{" +
                " status='" + getStatus() + "'" +
                ", message='" + getMessage() + "'" +
                "}";
    }

}
