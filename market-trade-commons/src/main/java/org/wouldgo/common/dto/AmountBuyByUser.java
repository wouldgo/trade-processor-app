package org.wouldgo.common.dto;

import java.math.BigDecimal;

/**
 * This represent an amount bought by an user. It stores for an user how much he sold (doesn't consider the currency).
 *
 * @author "wouldgo"
 *
 */
public class AmountBuyByUser {
	private final String userIdentifier;
	private final BigDecimal amountBought;

	/**
	 * Constructor that except all the values.
	 *
	 * @param userIdentifier the user identifier
	 * @param amountBought the amount bought by user
	 */
	public AmountBuyByUser(String userIdentifier, BigDecimal amountBought) {
		this.userIdentifier = userIdentifier;
		this.amountBought = amountBought;
	}

	/**
	 * @return the userIdentifier
	 */
	public final String getUserIdentifier() {
		return this.userIdentifier;
	}

	/**
	 * @return the amountBougth
	 */
	public final BigDecimal getAmountBought() {
		return this.amountBought;
	}
}
