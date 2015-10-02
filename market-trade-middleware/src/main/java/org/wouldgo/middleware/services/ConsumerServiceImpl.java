package org.wouldgo.middleware.services;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.stereotype.Component;
import org.wouldgo.common.bus.ApplicationBus;
import org.wouldgo.common.dto.TradeMessage;
import org.wouldgo.middleware.repositories.TradeMessageRepository;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/**
 * <p>The default implementation for the consumer service.</p>
 * <p>This implementation persist the trade messages in database and communicate to client subscribed the arrival of new elements.</p>
 *
 * @author "wouldgo"
 *
 */
@Component
final class ConsumerServiceImpl implements ConsumerService, ApplicationListener<BrokerAvailabilityEvent> {

	private static final transient Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);

	@Autowired
	private ApplicationBus bus;

	@Autowired
	private TradeMessageRepository tradeMessageRepository;

	@Autowired
	private MessageSendingOperations<String> messagingTemplate;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.wouldgo.middleware.services.services.ConsumerService#consumeElement()
	 */
	@Override
	@Subscribe
	@AllowConcurrentEvents
	public void consumeElement(TradeMessage aTradeMessage) {

		if (ConsumerServiceImpl.logger.isDebugEnabled()) {

			ConsumerServiceImpl.logger.debug("Consuming new element from application bus: {}", aTradeMessage);
		}

		if (aTradeMessage != null) {

			org.wouldgo.middleware.entities.TradeMessage toSave = new org.wouldgo.middleware.entities.TradeMessage(aTradeMessage.getUserId(),
					aTradeMessage.getCurrencyFrom(), aTradeMessage.getCurrencyTo(), aTradeMessage.getOriginatingCountry(), aTradeMessage.getTimePlaced(),
					aTradeMessage.getAmountSell(), aTradeMessage.getAmountBuy(), aTradeMessage.getRate());
			this.tradeMessageRepository.save(toSave);

			if (ConsumerServiceImpl.logger.isDebugEnabled()) {

				ConsumerServiceImpl.logger.debug("Trade message from application bus is now persistent in database.");
			}

			this.messagingTemplate.convertAndSend("/new-message", aTradeMessage);
			if (ConsumerServiceImpl.logger.isDebugEnabled()) {

				ConsumerServiceImpl.logger.debug("Trade message from application bus sent to messaging template.");
			}
		} else {

			if (ConsumerServiceImpl.logger.isErrorEnabled()) {

				ConsumerServiceImpl.logger.error("Element sent by application bus was null.");
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(BrokerAvailabilityEvent event) {

		if (ConsumerServiceImpl.logger.isDebugEnabled() &&
				(event != null)) {

			ConsumerServiceImpl.logger.debug("WebSocket Broker availability changed {}", Long.valueOf(event.getTimestamp()));
		}

	}

	@PostConstruct
	private void postConstruct() {
		this.bus.register(this);
	}
}
