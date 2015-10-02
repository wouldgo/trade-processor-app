package org.wouldgo.middleware.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wouldgo.middleware.entities.TradeMessage;

/**
 * Query repository component where the {@linkplain TradeMessage} related queries relies.
 *
 * @author "wouldgo"
 *
 */
public interface TradeMessageRepository extends JpaRepository<TradeMessage, Long>, TradeMessageRepositoryCustom {

	/**
	 * Finds the top 100 trade messages ordering by time placed decrescent.
	 *
	 * @return at most 100 {@linkplain TradeMessage}s
	 */
	public Collection<TradeMessage> findTop100ByOrderByTimePlacedDesc();
}
