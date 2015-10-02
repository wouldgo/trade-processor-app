package org.wouldgo.middleware.services;

import org.springframework.stereotype.Service;
import org.wouldgo.common.dto.TradeMessage;

/**
 * Consumer abstraction.
 *
 * @author "wouldgo"
 *
 */
@Service
public interface ConsumerService {

	/**
	 * Triggers the consuming mechanism.
	 *
	 * @param aTradeMessage the object to consume
	 */
	public void consumeElement(TradeMessage aTradeMessage);
}
