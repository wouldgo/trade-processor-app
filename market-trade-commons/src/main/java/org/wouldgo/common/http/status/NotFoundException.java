package org.wouldgo.common.http.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This runtime exception represent a 404 (Not Found) http status code.</p>
 *
 * @author "wouldgo"
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public final class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7078493792834439616L;

	/**
	 * @see RuntimeException#RuntimeException()
	 */
	public NotFoundException() {
		super();
	}

	/**
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see RuntimeException#RuntimeException(String)
	 *
	 * @param message the message
	 */
	public NotFoundException(String message) {
		super(message);
	}

	/**
	 * @see RuntimeException#RuntimeException(Throwable)
	 *
	 * @param cause the cause
	 */
	public NotFoundException(Throwable cause) {
		super(cause);
	}
}
