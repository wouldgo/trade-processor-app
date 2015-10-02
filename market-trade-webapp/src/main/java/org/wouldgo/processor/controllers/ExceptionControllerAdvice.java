package org.wouldgo.processor.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wouldgo.common.dto.ErrorMessage;
import org.wouldgo.common.utils.HttpConvertionUtil;

/**
 * {@linkplain ControllerAdvice} that manages all the exceptions raised by controllers.
 *
 * @author "wouldgo"
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

	private final static transient Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	/**
	 * {@linkplain ExceptionHandler} that manages all the exceptions raised by
	 * controllers
	 *
	 * @param ex the {@linkplain Exception} raised during execution
	 * @return the http response containing the error messages
	 *
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public @ResponseBody static ResponseEntity<ErrorMessage> handleAllException(Exception ex) {

		if (ExceptionControllerAdvice.logger.isErrorEnabled()) {

			ExceptionControllerAdvice.logger.error("Exception during controller invocation...", ex);
		}

		return HttpConvertionUtil.fromExceptionToStatusCode(ex);
	}
}
