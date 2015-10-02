package org.wouldgo.middleware.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.wouldgo.common.dto.AmountBuyByUser;
import org.wouldgo.common.dto.AmountSellByUser;
import org.wouldgo.common.dto.NationCounter;
import org.wouldgo.middleware.entities.TradeMessage;
import org.wouldgo.middleware.entities.TradeMessage_;

import com.google.common.collect.ImmutableList;

/**
 * Extension to {@linkplain TradeMessageRepository} that uses the Criteria API.
 *
 * @author "wouldgo"
 *
 */
final class TradeMessageRepositoryImpl implements TradeMessageRepositoryCustom {

	private final static transient Logger logger = Logger.getLogger(TradeMessageRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	/* (non-Javadoc)
	 * @see org.wouldgo.middleware.repositories.TradeMessageRepositoryCustom#countNations()
	 */
	@Override
	public Collection<NationCounter> countNations() {

		if (TradeMessageRepositoryImpl.logger.isTraceEnabled()) {

			TradeMessageRepositoryImpl.logger.trace("Counting the nations");
		}
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();

		Root<TradeMessage> root = criteriaQuery.from(TradeMessage.class);
		CriteriaQuery<Tuple> selectCountGroupByOriginatingCountry = criteriaQuery.multiselect(criteriaBuilder.count(root.get(TradeMessage_.originatingCountry)), root.get(TradeMessage_.originatingCountry))
				.distinct(true)
				.groupBy(root.get(TradeMessage_.originatingCountry))
				.orderBy(criteriaBuilder.desc(root.get(TradeMessage_.originatingCountry)));

		List<Tuple> queryResult = this.entityManager.createQuery(selectCountGroupByOriginatingCountry).getResultList();
		List<NationCounter> toReturn = new ArrayList<>();
		if (queryResult != null) {

			for (Tuple aTuple : queryResult) {

				if (aTuple != null) {

					List<TupleElement<?>> aTupleElements = aTuple.getElements();
					if ((aTupleElements != null) &&
							(aTupleElements.size() == 2)) {

						TupleElement<?> counter = aTupleElements.get(0);
						TupleElement<?> value = aTupleElements.get(1);
						NationCounter aNationCounter = new NationCounter((String)aTuple.get(value), ((Long)aTuple.get(counter)).longValue());
						toReturn.add(aNationCounter);
					}
				}
			}
		}

		return ImmutableList.<NationCounter>copyOf(toReturn);
	}

	/* (non-Javadoc)
	 * @see org.wouldgo.middleware.repositories.TradeMessageRepositoryCustom#amountSellByUser()
	 */
	@Override
	public Collection<AmountSellByUser> amountSellByUser() {

		if (TradeMessageRepositoryImpl.logger.isTraceEnabled()) {

			TradeMessageRepositoryImpl.logger.trace("Giving amount sold");
		}

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();

		Root<TradeMessage> root = criteriaQuery.from(TradeMessage.class);
		CriteriaQuery<Tuple> selectCountGroupByUserId = criteriaQuery.multiselect(criteriaBuilder.sum(root.get(TradeMessage_.amountSell)), root.get(TradeMessage_.userId))
				.groupBy(root.get(TradeMessage_.userId))
				.orderBy(criteriaBuilder.desc(root.get(TradeMessage_.userId)));

		List<Tuple> queryResult = this.entityManager.createQuery(selectCountGroupByUserId).getResultList();
		List<AmountSellByUser> toReturn = new ArrayList<>();
		if (queryResult != null) {

			for (Tuple aTuple : queryResult) {

				if (aTuple != null) {

					List<TupleElement<?>> aTupleElements = aTuple.getElements();
					if ((aTupleElements != null) &&
							(aTupleElements.size() == 2)) {

						TupleElement<?> value = aTupleElements.get(0);
						TupleElement<?> key = aTupleElements.get(1);
						AmountSellByUser anAmountSellByUser = new AmountSellByUser((String)aTuple.get(key), (BigDecimal)aTuple.get(value));
						toReturn.add(anAmountSellByUser);
					}
				}
			}
		}
		return ImmutableList.copyOf(toReturn);
	}

	/* (non-Javadoc)
	 * @see org.wouldgo.middleware.repositories.TradeMessageRepositoryCustom#amountBoughtByUser()
	 */
	@Override
	public Collection<AmountBuyByUser> amountBoughtByUser() {

		if (TradeMessageRepositoryImpl.logger.isTraceEnabled()) {

			TradeMessageRepositoryImpl.logger.trace("Giving amount bought");
		}

		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();

		Root<TradeMessage> root = criteriaQuery.from(TradeMessage.class);
		CriteriaQuery<Tuple> selectCountGroupByUserId = criteriaQuery.multiselect(criteriaBuilder.sum(root.get(TradeMessage_.amountBuy)), root.get(TradeMessage_.userId))
				.groupBy(root.get(TradeMessage_.userId))
				.orderBy(criteriaBuilder.desc(root.get(TradeMessage_.userId)));

		List<Tuple> queryResult = this.entityManager.createQuery(selectCountGroupByUserId).getResultList();
		List<AmountBuyByUser> toReturn = new ArrayList<>();
		if (queryResult != null) {

			for (Tuple aTuple : queryResult) {

				if (aTuple != null) {

					List<TupleElement<?>> aTupleElements = aTuple.getElements();
					if ((aTupleElements != null) &&
							(aTupleElements.size() == 2)) {

						TupleElement<?> value = aTupleElements.get(0);
						TupleElement<?> key = aTupleElements.get(1);
						AmountBuyByUser anAmountSellByUser = new AmountBuyByUser((String)aTuple.get(key), (BigDecimal)aTuple.get(value));
						toReturn.add(anAmountSellByUser);
					}
				}
			}
		}
		return ImmutableList.copyOf(toReturn);
	}
}
