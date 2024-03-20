package br.com.cursoudemy.productapi.exceptions;
import java.util.Date;
import java.util.List;

public class ExceptionDatails {

	private Date timestamp;
  private int status;
	private String message;
	private String details;
	private List<String> errors;

	public ExceptionDatails(Date timestamp, int status, String message, String details) {
		this.timestamp = timestamp;
    this.status = status;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

  public int getStatus() {
    return status;
  }

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
