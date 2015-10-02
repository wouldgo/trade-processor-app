package org.wouldgo.common.http.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This runtime exception represent a 400 (Bad Request) http status code.</p>
 *
 * @author "wouldgo"
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public final class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 3955906515261203788L;

	/**
	 * @see RuntimeException#RuntimeException()
	 */
	public BadRequestException() {
		super();
	}

	/**
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see RuntimeException#RuntimeException(String)
	 *
	 * @param message the message
	 */
	public BadRequestException(String message) {
		super(message);
	}

	/**
	 * @see RuntimeException#RuntimeException(Throwable)
	 *
	 * @param cause the cause
	 */
	public BadRequestException(Throwable cause) {
		super(cause);
	}
}
