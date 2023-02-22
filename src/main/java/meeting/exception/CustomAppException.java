package meeting.exception;

import org.springframework.http.HttpStatus;

public class CustomAppException extends RuntimeException {

	
	
	private static final long serialVersionUID = 1L;
	private String status;
	private HttpStatus responseStatus;
	
	public CustomAppException() {

	}

	public CustomAppException( String status, String message, HttpStatus responseStatus ) {
		super(message);
		
		this.status = status;
		this.responseStatus = responseStatus;
	}

	public CustomAppException(Exception exception) {
		super(exception);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public HttpStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(HttpStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	
	
}
