package org.wouldgo.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This represent an error message that can be managed by http controllers callers.
 *
 * @author "wouldgo"
 *
 */
public final class ErrorMessage {

	@JsonProperty("code")
	private final long errorCode;
	@JsonProperty("errorMessage")
	private final String errorMessage;

	/**
	 * Constructor that expects an error codification and a descriptive message.
	 *
	 * @param errorCode error codification.
	 * @param message descriptive message.
	 */
	public ErrorMessage(long errorCode, String message) {
		this.errorCode = errorCode;
		this.errorMessage = message;
	}

	/**
	 * @return the errorCode
	 */
	public final long getErrorCode() {
		return this.errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public final String getErrorMessage() {
		return this.errorMessage;
	}
}
