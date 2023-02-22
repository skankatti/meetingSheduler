package meeting.exception;

import org.springframework.http.HttpStatus;

public class CustomAppExceptionWithData extends RuntimeException {

	private static final long serialVersionUID = 5975982113690289643L;

	// Status would be informatory message in screaming camel case
	private String status;
	private HttpStatus responseStatus;
	private Object data;

	public CustomAppExceptionWithData() {

	}

	public CustomAppExceptionWithData(String status, String message, HttpStatus responseStatus, Object data) {
		super(message);
		this.status = status;
		this.responseStatus = responseStatus;
		this.data = data;
	}

	public CustomAppExceptionWithData(Exception exception) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	

}
