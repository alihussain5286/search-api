package com.axiom.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.axiom.example.util.ErrorConstants;

@RestControllerAdvice
public class ApiExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public Object handleException(Exception exception) {
		LOGGER.error("Exception thrown: ", exception);
		return new Error(ErrorConstants.INTERNAL_ERROR,
				messageSource.getMessage(ErrorConstants.INTERNAL_ERROR, null, LocaleContextHolder.getLocale()));
	}

	@ExceptionHandler({ ApiException.class })
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	public Object handleCustomException(ApiException ex) {
		LOGGER.error("ApiException thrown: ", ex);
		return new Error(ex.getErrorCode(),
				messageSource.getMessage(ex.getErrorCode(), null, LocaleContextHolder.getLocale()));
	}

}
