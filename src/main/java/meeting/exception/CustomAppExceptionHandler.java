package meeting.exception;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomAppExceptionHandler {

	@ExceptionHandler(CustomAppException.class)
	public ResponseEntity<?> appExceptionResponse(CustomAppException cae) {
		Response<HashMap> response = new Response<HashMap>();

		response.setStatus(cae.getStatus());
		response.setMessage(cae.getMessage());
		response.setData(new HashMap());

		return new ResponseEntity<>(response, cae.getResponseStatus());
	}

	@ExceptionHandler(CustomAppExceptionWithData.class)
	public ResponseEntity<?> appExceptionResponsewithData(CustomAppExceptionWithData cae) {
		Response response = new Response();
		response.setStatus(cae.getStatus());
		response.setMessage(cae.getMessage());
		response.setData(cae.getData());

		return new ResponseEntity<>(response, cae.getResponseStatus());
	}
	
	
	
	
	
}
