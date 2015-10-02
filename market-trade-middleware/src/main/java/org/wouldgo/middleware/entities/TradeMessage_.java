package org.wouldgo.middleware.entities;

import java.math.BigDecimal;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import org.joda.time.DateTime;

/**
 * Trade message metamodel, useful in criteria query definition.
 *
 * @author "wouldgo"
 *
 */
@edu.umd.cs.findbugs.annotations.SuppressWarnings
@StaticMetamodel(TradeMessage.class)
public class TradeMessage_ {

	public static volatile SingularAttribute<TradeMessage, Long> tradeMessageIdentifier;
	public static volatile SingularAttribute<TradeMessage, String> userId, currencyFrom, currencyTo, originatingCountry;
	public static volatile SingularAttribute<TradeMessage, DateTime> timePlaced;
	public static volatile SingularAttribute<TradeMessage, BigDecimal> amountSell, amountBuy, rate;
}
