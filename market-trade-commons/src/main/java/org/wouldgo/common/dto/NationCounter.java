package org.wouldgo.common.dto;

/**
 * This represent a nation counter element. It stores for a nation the how many time this occurs in database.
 *
 * @author "wouldgo"
 *
 */
public final class NationCounter {

	private final String nation;
	private final long counter;

	/**
	 * Constructor that except all the values.
	 *
	 * @param nation the nation
	 * @param counter the occurrences
	 */
	public NationCounter(String nation, long counter) {
		this.nation = nation;
		this.counter = counter;
	}

	/**
	 * @return the nation
	 */
	public final String getNation() {
		return this.nation;
	}

	/**
	 * @return the counter
	 */
	public final long getCounter() {
		return this.counter;
	}
}
