package org.wouldgo.middleware.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * The trade message entity that represent the table in the database.
 *
 * @author "wouldgo"
 *
 */
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "trade_messages")
public class TradeMessage implements Serializable {

	private static final long serialVersionUID = 6563769274351978404L;

	@Id
	@SequenceGenerator(name="trade_message_seq", allocationSize=5, initialValue=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trade_message_seq")
	@Column(name = "trade_message_id")
	private long tradeMessageIdentifier;

	@Column(name = "user_identifier")
	private final String userId;

	@Column(name = "currency_from")
	private final String currencyFrom;

	@Column(name = "currency_to")
	private final String currencyTo;

	@Column(name = "origin_country")
	private final String originatingCountry;

	@Column(name = "transaction_time")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime",
	parameters = {
			@Parameter(name = "databaseZone", value = "UTC"),
			@Parameter(name = "javaZone", value = "jvm")
	})
	private final DateTime timePlaced;

	@Column(name = "amount_sold")
	private final BigDecimal amountSell;

	@Column(name = "amount_bougth")
	private final BigDecimal amountBuy;

	@Column(name = "rate")
	private final BigDecimal rate;

	/**
	 * Default constructor used by JPA
	 */
	TradeMessage() {
		this(null, null, null, null, null, null, null, null);
	}

	/**
	 * Creates a {@linkplain TradeMessage} passing all its parameters.
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
			String currencyFrom, String currencyTo, String originatingCountry,
			DateTime timePlaced, BigDecimal amountSell, BigDecimal amountBuy,
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
}