package org.wouldgo.common.dto;

import java.math.BigDecimal;

/**
 * This represent an amount sold by an user. It stores for an user how much he sold (doesn't consider the currency).
 *
 * @author "wouldgo"
 *
 */
public final class AmountSellByUser {
	private final String userIdentifier;
	private final BigDecimal amountSold;

	/**
	 * Constructor that except all the values.
	 *
	 * @param userIdentifier the user identifier
	 * @param amountSold the amount sold by user
	 */
	public AmountSellByUser(String userIdentifier, BigDecimal amountSold) {
		this.userIdentifier = userIdentifier;
		this.amountSold = amountSold;
	}

	/**
	 * @return the userIdentifier
	 */
	public final String getUserIdentifier() {
		return this.userIdentifier;
	}

	/**
	 * @return the amountSold
	 */
	public final BigDecimal getAmountSold() {
		return this.amountSold;
	}
}
