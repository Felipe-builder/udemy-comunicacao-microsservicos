package br.com.cursoudemy.productapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Objects;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {

  public ValidationException(String message) {
    super(message);
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ValidationException)) {
            return false;
        }
        ValidationException validationExcaption = (ValidationException) o;
        return Objects.equals(this, validationExcaption);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return "{" +
      "}";
  }

}
