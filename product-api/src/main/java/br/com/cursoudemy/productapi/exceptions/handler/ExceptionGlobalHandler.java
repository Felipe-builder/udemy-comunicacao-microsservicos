package br.com.cursoudemy.productapi.exceptions.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cursoudemy.productapi.exceptions.AuthenticationException;
import br.com.cursoudemy.productapi.exceptions.ExceptionDatails;
import br.com.cursoudemy.productapi.exceptions.NotFoundException;
import br.com.cursoudemy.productapi.exceptions.ValidationException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

@ControllerAdvice
public class ExceptionGlobalHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    List<String> errors = new ArrayList<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }
    // Convert the list of errors to a string
    String errorDetails = String.join(", ", errors);
    ExceptionDatails apiError = new ExceptionDatails(new Date(), 400, "Invalid request content.", errorDetails);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    ExceptionDatails errorDetails = new ExceptionDatails(
        new Date(),
        status.value(),
        ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ExceptionDatails> handleAllExceptions(Exception ex, WebRequest request) {
    ExceptionDatails exceptionResponse = new ExceptionDatails(new Date(), 500, ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(NotFoundException.class)
  public final ResponseEntity<ExceptionDatails> handleNotFoundExceptions(Exception ex, WebRequest request) {
    ExceptionDatails exceptionResponse = new ExceptionDatails(new Date(), 404, ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ValidationException.class)
  public final ResponseEntity<ExceptionDatails> handleValidationExceptions(Exception ex, WebRequest request) {
    ExceptionDatails exceptionResponse = new ExceptionDatails(new Date(), 400, ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AuthenticationException.class)
  public final ResponseEntity<ExceptionDatails> handleAuthenticationExceptions(Exception ex, WebRequest request) {
    ExceptionDatails exceptionResponse = new ExceptionDatails(new Date(), 401, ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public final ResponseEntity<ExceptionDatails> handleDataIntegrityViolationExceptions(DataIntegrityViolationException ex,
   WebRequest request) {
    String errorMessage = "Data integrity error: " + ex.getMostSpecificCause().getMessage();
    ExceptionDatails exceptionResponse = new ExceptionDatails(new Date(), 400, errorMessage,
        request.getDescription(false));
    return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({MalformedJwtException.class, SignatureException.class})
  public final ResponseEntity<ExceptionDatails> handleJwtExceptions(Exception ex,
     WebRequest request) {
      String errorMessage = "Error while trying to proccess the Access Token.";
      ExceptionDatails exceptionResponse = new ExceptionDatails(new Date(), HttpStatus.UNAUTHORIZED.value(), errorMessage,
          request.getDescription(false));
      return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
  }
}
