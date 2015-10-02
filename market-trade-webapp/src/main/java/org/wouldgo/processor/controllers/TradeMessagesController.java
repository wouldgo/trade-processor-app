package org.wouldgo.processor.controllers;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wouldgo.common.dto.AmountBuyByUser;
import org.wouldgo.common.dto.AmountSellByUser;
import org.wouldgo.common.dto.NationCounter;
import org.wouldgo.common.dto.TradeMessage;
import org.wouldgo.middleware.services.TradeMessagesService;

/**
 * Controller that expose to the clients the informations about trade messages.
 *
 * @author "wouldgo"
 *
 */
@Controller
@RequestMapping("/api")
public class TradeMessagesController {

	private final static transient Logger logger = LoggerFactory.getLogger(TradeMessagesController.class);

	@Autowired
	private TradeMessagesService tradeMessagesService;

	/**
	 * Endpoint that inquiry the system requesting (via {@linkplain TradeMessagesService#getLatestHundredTradeMessages()}) the
	 * latest 100 trade messages.
	 *
	 * @return at most 100 trade messages.
	 */
	@RequestMapping(value = "/trade-messages", method = RequestMethod.GET)
	public @ResponseBody Collection<TradeMessage> getAllTheLatestHundredTradeMessages() {

		if (TradeMessagesController.logger.isInfoEnabled()) {

			TradeMessagesController.logger.info("Called GET - /trade-messages");
		}
		return this.tradeMessagesService.getLatestHundredTradeMessages();
	}

	/**
	 * Endpoint that inquiry the system requesting (via {@linkplain TradeMessagesService#getNationThatOriginateTradeMessages()}) the
	 * nations that originate at least a trade message, counting the occurrence of these.
	 *
	 * @return the nation codes with them occurrence.
	 */
	@RequestMapping(value = "/nations", method = RequestMethod.GET)
	public @ResponseBody Collection<NationCounter> getNationThatOriginateTradeMessages() {

		if (TradeMessagesController.logger.isInfoEnabled()) {

			TradeMessagesController.logger.info("Called GET - /nations");
		}
		return this.tradeMessagesService.getNationThatOriginateTradeMessages();
	}

	/**
	 * Endpoint that inquiry the system requesting (via {@linkplain TradeMessagesService#getAmountSellByUser()}).
	 *
	 * @return the information about amount sold by each user.
	 */
	@RequestMapping(value = "/amount-sell", method = RequestMethod.GET)
	public @ResponseBody Collection<AmountSellByUser> getAmountSellByUser() {

		if (TradeMessagesController.logger.isInfoEnabled()) {

			TradeMessagesController.logger.info("Called GET - /amount-sell");
		}
		return this.tradeMessagesService.getAmountSellByUser();
	}

	/**
	 * Endpoint that inquiry the system requesting (via {@linkplain TradeMessagesService#getAmountBuyByUser()}).
	 *
	 * @return the information about amount bought by each user.
	 */
	@RequestMapping(value = "/amount-buy", method = RequestMethod.GET)
	public @ResponseBody Collection<AmountBuyByUser> getAmountBuyByUser() {

		if (TradeMessagesController.logger.isInfoEnabled()) {

			TradeMessagesController.logger.info("Called GET - /amount-buy");
		}
		return this.tradeMessagesService.getAmountBuyByUser();
	}
}
