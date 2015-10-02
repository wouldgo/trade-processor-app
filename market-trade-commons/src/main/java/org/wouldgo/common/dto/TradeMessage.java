package org.wouldgo.common.dto;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>This represent the message that arrives from outer systems.</p>
 * <p>It contains the informations needed to identify a trade message.</p>
 *
 * @author "wouldgo"
 *
 */
public final class TradeMessage {

	private static transient DateTimeFormatter dateTimeFormatter;
	static {
		Properties properties = new Properties();

		try (InputStream confPropFile = TradeMessage.class.getClassLoader()
				.getResourceAsStream("common-confs.properties")) {

			properties.load(confPropFile);
		} catch (IOException e) {

			throw new IllegalStateException(e);
		}

		TradeMessage.dateTimeFormatter = DateTimeFormat.forPattern(properties
				.getProperty("dateformat.trademessage"));
	}

	private final String userId, currencyFrom, currencyTo, originatingCountry;
	private final DateTime timePlaced;
	private final BigDecimal amountSell, amountBuy, rate;


	/**
	 * Constructor that expects all the parameters to be valorized.
	 *
	 * @param userId user identification
	 * @param currencyFrom currency from trade arrives
	 * @param currencyTo currency to trade comes
	 * @param originatingCountry country that originates the trade message
	 * @param timePlaced the time this movement is done
	 * @param amountSell the amount sold
	 * @param amountBuy the amount bought
	 * @param rate the exchange rate
	 */
	public TradeMessage(String userId,
			String currencyFrom,
			String currencyTo,
			String originatingCountry,
			DateTime timePlaced,
			BigDecimal amountSell,
			BigDecimal amountBuy,
			BigDecimal rate) {

		this.userId = userId;
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.originatingCountry = originatingCountry;
		this.timePlaced = timePlaced;
		this.amountSell = amountSell;
		this.amountBuy = amountBuy;
		this.rate = rate;
	}


	/**
	 * Constructor used by Jackson
	 *
	 * @param userId user identification
	 * @param currencyFrom currency from trade arrives
	 * @param currencyTo currency to trade comes
	 * @param originatingCountry country that originates the trade message
	 * @param timePlaced the time this movement is done
	 * @param amountSell the amount sold
	 * @param amountBuy the amount bought
	 * @param rate the exchange rate
	 */
	@JsonCreator
	private TradeMessage(@JsonProperty("userId") String userId,
			@JsonProperty("currencyFrom") String currencyFrom,
			@JsonProperty("currencyTo") String currencyTo,
			@JsonProperty("originatingCountry") String originatingCountry,
			@JsonProperty("timePlaced") String timePlaced,
			@JsonProperty("amountSell") BigDecimal amountSell,
			@JsonProperty("amountBuy") BigDecimal amountBuy,
			@JsonProperty("rate") BigDecimal rate) {

		this(userId,
				currencyFrom,
				currencyTo,
				originatingCountry,
				DateTime.parse(timePlaced, TradeMessage.dateTimeFormatter),
				amountSell,
				amountBuy,
				rate);
	}

	/**
	 * @return the amountBuy
	 */
	public final BigDecimal getAmountBuy() {
		return this.amountBuy;
	}

	/**
	 * @return the amountSell
	 */
	public final BigDecimal getAmountSell() {
		return this.amountSell;
	}

	/**
	 * @return the currencyFrom
	 */
	public final String getCurrencyFrom() {
		return this.currencyFrom;
	}

	/**
	 * @return the currencyTo
	 */
	public final String getCurrencyTo() {
		return this.currencyTo;
	}

	/**
	 * @return the originatingCountry
	 */
	public final String getOriginatingCountry() {
		return this.originatingCountry;
	}

	/**
	 * @return the rate
	 */
	public final BigDecimal getRate() {
		return this.rate;
	}

	/**
	 * @return the timePlaced
	 */
	public final DateTime getTimePlaced() {
		return this.timePlaced;
	}

	/**
	 * @return the userId
	 */
	public final String getUserId() {
		return this.userId;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String separator = " | ";
		StringBuffer sb = new StringBuffer();
		sb.append("userId -> ");
		sb.append(this.userId);
		sb.append(separator);
		sb.append("currencyFrom -> ");
		sb.append(this.currencyFrom);
		sb.append(separator);
		sb.append("currencyTo -> ");
		sb.append(this.currencyTo);
		sb.append(separator);
		sb.append("originatingCountry -> ");
		sb.append(this.originatingCountry);
		sb.append(separator);
		sb.append("timePlaced -> ");
		sb.append(this.timePlaced);
		sb.append(separator);
		sb.append("amountSell -> ");
		sb.append(this.amountSell);
		sb.append(separator);
		sb.append("amountBuy -> ");
		sb.append(this.amountBuy);
		sb.append(separator);
		sb.append("rate -> ");
		sb.append(this.rate);
		return sb.toString();
	}
}
