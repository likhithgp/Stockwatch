package com.learn.stockwatch.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StockwatchCentralExceptionHandling extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception) {
		return new ResponseEntity<String>("No Data found for given Input Data", HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> hadleIllegalArgumentException(IllegalArgumentException exception) {
		return new ResponseEntity<String>("No Data found for given Input Data", HttpStatus.NOT_FOUND);

	}
	

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Object>("Try with Approiate http method", HttpStatus.NOT_ACCEPTABLE);
	}

}
