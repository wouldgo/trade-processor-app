package org.wouldgo.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.wouldgo.common.dto.ErrorMessage;

/**
 * Utility class used to manage conversions
 *
 * @author "wouldgo"
 *
 */
public class HttpConvertionUtil {

	private final static transient Logger logger = LoggerFactory.getLogger(HttpConvertionUtil.class);

	/**
	 * <p>Utility method used to build up a {@linkplain ResponseEntity} from an exception.</p>
	 * <p>This checks if the exception caught is annotated with {@linkplain ResponseStatus} and if yes uses that http status code.</p>
	 * Then builds an {@linkplain ErrorMessage} with the exception message.
	 *
	 * @param exception the exception caught.
	 * @return the {@linkplain ResponseEntity} used to return to client caller the error.
	 */
	public static final ResponseEntity<ErrorMessage> fromExceptionToStatusCode(Throwable exception) {

		if (HttpConvertionUtil.logger.isDebugEnabled()) {

			HttpConvertionUtil.logger.debug("Exception arrived... Begin trasformation to http error");
		}
		ErrorMessage errorMessageToReturn = new ErrorMessage(Long.MIN_VALUE, "N/A");
		if (exception != null) {

			Class<? extends Throwable> exceptionClazz = exception.getClass();
			if (exceptionClazz.isAnnotationPresent(ResponseStatus.class)) {

				ResponseStatus responseStatusAnnotation = exceptionClazz.getAnnotation(ResponseStatus.class);
				HttpStatus httpStatus = responseStatusAnnotation.value();

				errorMessageToReturn = new ErrorMessage(0l, exception.getMessage());
				if (httpStatus != null) {

					return new ResponseEntity<>(errorMessageToReturn, httpStatus);
				}
			} else if (HttpConvertionUtil.logger.isTraceEnabled()) {

				HttpConvertionUtil.logger.trace("Exception isn't annotated with ResponseStatus");
			}

			errorMessageToReturn = new ErrorMessage(0l, exception.getMessage());
		} else if (HttpConvertionUtil.logger.isWarnEnabled()) {

			HttpConvertionUtil.logger.warn("Exception is null. Fallback on the default \"N/A\" 500 error");
		}

		return new ResponseEntity<>(errorMessageToReturn, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
