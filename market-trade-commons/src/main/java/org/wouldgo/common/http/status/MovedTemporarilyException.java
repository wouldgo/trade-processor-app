package org.wouldgo.common.http.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This runtime exception represent a 302 (Found/Moved temporarily) http status code.</p>
 *
 * @author "wouldgo"
 *
 */
@ResponseStatus(value = HttpStatus.FOUND)
public final class MovedTemporarilyException extends RuntimeException {

	private static final long serialVersionUID = -1088017476220053513L;

	/**
	 * @see RuntimeException#RuntimeException()
	 */
	public MovedTemporarilyException() {
		super();
	}

	/**
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public MovedTemporarilyException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see RuntimeException#RuntimeException(String)
	 *
	 * @param message the message
	 */
	public MovedTemporarilyException(String message) {
		super(message);
	}

	/**
	 * @see RuntimeException#RuntimeException(Throwable)
	 *
	 * @param cause the cause
	 */
	public MovedTemporarilyException(Throwable cause) {
		super(cause);
	}
}
