package com.midas.logging.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.midas.logging.error.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<String> handleBadRequestException(Exception e) {
		log.info("Bad request exception occurred. {}", e.getMessage(), e);

		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({NotFoundException.class})
	public ResponseEntity<String> handleNotFoundException(Exception e) {
		log.warn("Not found exception occurred. {}", e.getMessage(), e);

		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({Exception.class, RuntimeException.class})
	public ResponseEntity<String> handleAllException(Exception e) {
		log.error("Unexpected exception occurred. {}", e.getMessage(), e);

		return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
