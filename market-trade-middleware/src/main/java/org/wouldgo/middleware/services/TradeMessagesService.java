package org.wouldgo.middleware.services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.wouldgo.common.dto.AmountBuyByUser;
import org.wouldgo.common.dto.AmountSellByUser;
import org.wouldgo.common.dto.NationCounter;
import org.wouldgo.common.dto.TradeMessage;

/**
 * Service abstraction for the functionalities given to clients regards the {@linkplain TradeMessage}s.
 *
 * @author "wouldgo"
 *
 */
@Service
public interface TradeMessagesService {

	/**
	 * Returns at most the latest hundred trade messages.
	 *
	 * @return at most the latest hundred trade messages.
	 */
	public Collection<TradeMessage> getLatestHundredTradeMessages();


	/**
	 * Returns the nations that generated trade messages, counting the occurrence of these.
	 *
	 * @return a collection of nations code that generated trade messages with them occurrence.
	 */
	public Collection<NationCounter> getNationThatOriginateTradeMessages();

	/**
	 * Returns the amounts sold by each user.
	 *
	 * @return a collection containing the information about amount sold by each user.
	 */
	public Collection<AmountSellByUser> getAmountSellByUser();

	/**
	 * Returns the amounts bought by each user.
	 *
	 * @return a collection containing the information about amount bought by each user.
	 */
	public Collection<AmountBuyByUser> getAmountBuyByUser();
}
