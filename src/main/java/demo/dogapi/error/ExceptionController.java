package demo.dogapi.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.dogapi.domain.ResponseError;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResponseEntity<ResponseError> handleException(ServiceException e) {
		return new ResponseEntity<ResponseError>(new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseBody
	public ResponseEntity<ResponseError> handleException(NotFoundException e) {
		return new ResponseEntity<ResponseError>(new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
	}
}
