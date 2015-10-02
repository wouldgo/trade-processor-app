package org.wouldgo.middleware.services;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wouldgo.common.dto.AmountBuyByUser;
import org.wouldgo.common.dto.AmountSellByUser;
import org.wouldgo.common.dto.NationCounter;
import org.wouldgo.common.dto.TradeMessage;
import org.wouldgo.middleware.repositories.TradeMessageRepository;
import org.wouldgo.middleware.utils.Transformers;

import com.google.common.collect.ImmutableList;

/**
 * <p>The default implementation for the trade messages service.</p>
 * <p>This implementations uses the {@linkplain TradeMessageRepository} for inquiry the database.</p>
 *
 * @author "wouldgo"
 *
 */
@Component
final class TradeMessagesServiceImpl implements TradeMessagesService {

	private final static transient Logger logger = LoggerFactory.getLogger(TradeMessagesServiceImpl.class);

	@Autowired
	private TradeMessageRepository tradeMessageRepository;

	/* (non-Javadoc)
	 * @see org.wouldgo.middleware.services.TradeMessagesService#getLatestHundredTradeMessages()
	 */
	@Override
	public Collection<TradeMessage> getLatestHundredTradeMessages() {

		if (TradeMessagesServiceImpl.logger.isDebugEnabled()) {

			TradeMessagesServiceImpl.logger.debug("Getting the latest hundred trade messages");
		}
		Collection<org.wouldgo.middleware.entities.TradeMessage> result = this.tradeMessageRepository.findTop100ByOrderByTimePlacedDesc();
		Collection<TradeMessage> toReturn = new ArrayList<>();
		if (result != null) {

			for (org.wouldgo.middleware.entities.TradeMessage aTradeMessageEntity : result) {

				if (aTradeMessageEntity != null) {

					TradeMessage aTradeMessageDto = Transformers.fromEntityToDataTransferObject(aTradeMessageEntity);
					toReturn.add(aTradeMessageDto);
				} else {

					if (TradeMessagesServiceImpl.logger.isWarnEnabled()) {

						TradeMessagesServiceImpl.logger.warn("An trade message entity is null");
					}
				}
			}
		} else if (TradeMessagesServiceImpl.logger.isWarnEnabled()) {

			TradeMessagesServiceImpl.logger.warn("Query result returns null");
		}

		return ImmutableList.copyOf(toReturn);
	}

	/* (non-Javadoc)
	 * @see org.wouldgo.middleware.services.TradeMessagesService#getNationThatOriginateTradeMessages()
	 */
	@Override
	public Collection<NationCounter> getNationThatOriginateTradeMessages() {

		if (TradeMessagesServiceImpl.logger.isDebugEnabled()) {

			TradeMessagesServiceImpl.logger.debug("Getting the nations that have generated trade messages");
		}
		Collection<NationCounter> queryResult = this.tradeMessageRepository.countNations();
		if (queryResult != null) {

			return ImmutableList.<NationCounter>copyOf(queryResult);
		}
		return ImmutableList.<NationCounter>of();
	}

	/* (non-Javadoc)
	 * @see org.wouldgo.middleware.services.TradeMessagesService#getAmountSellByUser()
	 */
	@Override
	public Collection<AmountSellByUser> getAmountSellByUser() {

		if (TradeMessagesServiceImpl.logger.isDebugEnabled()) {

			TradeMessagesServiceImpl.logger.debug("Getting the users amount sold");
		}
		return ImmutableList.copyOf(this.tradeMessageRepository.amountSellByUser());
	}

	/* (non-Javadoc)
	 * @see org.wouldgo.middleware.services.TradeMessagesService#getAmountBuyByUser()
	 */
	@Override
	public Collection<AmountBuyByUser> getAmountBuyByUser() {

		if (TradeMessagesServiceImpl.logger.isDebugEnabled()) {

			TradeMessagesServiceImpl.logger.debug("Getting the users amount bought");
		}
		return ImmutableList.copyOf(this.tradeMessageRepository.amountBoughtByUser());
	}
}
