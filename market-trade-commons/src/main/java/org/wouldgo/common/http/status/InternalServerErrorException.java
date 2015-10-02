package org.wouldgo.common.http.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This runtime exception represent a 500 (Internal Server Error) http status code.</p>
 *
 * @author "wouldgo"
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public final class InternalServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 923265686989962104L;

	/**
	 * @see RuntimeException#RuntimeException()
	 */
	public InternalServerErrorException() {
		super();
	}

	/**
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public InternalServerErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see RuntimeException#RuntimeException(String)
	 *
	 * @param message the message
	 */
	public InternalServerErrorException(String message) {
		super(message);
	}

	/**
	 * @see RuntimeException#RuntimeException(Throwable)
	 *
	 * @param cause the cause
	 */
	public InternalServerErrorException(Throwable cause) {
		super(cause);
	}
}
