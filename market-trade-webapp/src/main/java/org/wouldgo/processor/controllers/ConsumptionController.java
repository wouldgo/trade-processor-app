package org.wouldgo.processor.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wouldgo.common.bus.ApplicationBus;
import org.wouldgo.common.dto.TradeMessage;

/**
 * Controller that manages the consumption endpoint
 *
 * @author "wouldgo"
 *
 */
@Controller
@RequestMapping("/api")
public final class ConsumptionController {

	private static final transient Logger logger = LoggerFactory.getLogger(ConsumptionController.class);
	@Autowired
	private ApplicationBus bus;

	/**
	 * Endpoint invoked by external systems to feed the application.
	 *
	 * @param aTradeMessage the trade message sent by external systems.
	 */
	@RequestMapping(value = "/trade-message", method = RequestMethod.POST)
	public @ResponseBody void consumeMessageTrade(@RequestBody TradeMessage aTradeMessage) {

		if (ConsumptionController.logger.isInfoEnabled()) {

			ConsumptionController.logger.info("Called POST - /trade-message with {}", aTradeMessage);
		}
		this.bus.publish(aTradeMessage);
	}

	/**
	 * Simple ping/pong endpoint used for testing.
	 *
	 * @return "pong!"
	 */
	@RequestMapping(value = "ping")
	public @ResponseBody static String ping() {
		return "pong!";
	}
}
