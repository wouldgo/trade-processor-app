package org.wouldgo.middleware.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wouldgo.common.dto.AmountBuyByUser;
import org.wouldgo.common.dto.AmountSellByUser;
import org.wouldgo.common.dto.NationCounter;

/**
 * Extention to the spring's {@linkplain JpaRepository} where custom queries can be put.
 *
 * @author "wouldgo"
 *
 */
interface TradeMessageRepositoryCustom {

	/**
	 * Select distinctively the nations that originates the trade messages, counting the occurrence of these.
	 *
	 * @return the nations that originate trade messages with them occurrence.
	 */
	Collection<NationCounter> countNations();

	/**
	 * Counting all the amount sold by all users.
	 *
	 * @return the users collection with theirs amount sold.
	 */
	Collection<AmountSellByUser> amountSellByUser();

	/**
	 * Counting all the amount bought by all users.
	 *
	 * @return the users collection with theirs amount bought.
	 */
	Collection<AmountBuyByUser> amountBoughtByUser();
}
