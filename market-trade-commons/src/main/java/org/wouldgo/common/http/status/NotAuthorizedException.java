package org.wouldgo.common.http.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * <p>This runtime exception represent a 401 (Not Authorized) http status code.</p>
 *
 * @author "wouldgo"
 *
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public final class NotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = 4816249827100857587L;

	/**
	 * @see RuntimeException#RuntimeException()
	 */
	public NotAuthorizedException() {
		super();
	}

	/**
	 * @see RuntimeException#RuntimeException(String, Throwable)
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public NotAuthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see RuntimeException#RuntimeException(String)
	 *
	 * @param message the message
	 */
	public NotAuthorizedException(String message) {
		super(message);
	}

	/**
	 * @see RuntimeException#RuntimeException(Throwable)
	 *
	 * @param cause the cause
	 */
	public NotAuthorizedException(Throwable cause) {
		super(cause);
	}
}
