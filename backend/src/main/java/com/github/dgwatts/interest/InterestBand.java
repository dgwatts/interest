package com.github.dgwatts.interest;

public class InterestBand implements Comparable <InterestBand>{

	private long lowerBound;
	private Long upperBound;
	private int interestRate;
	private long interestEarned;

	public InterestBand() {	}

	public InterestBand(long lowerBound, Long upperBound, int interestRate) {
		this(lowerBound, upperBound, interestRate, 0L);
	}

	public InterestBand(long lowerBound, Long upperBound, int interestRate, long interestEarned) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.interestRate = interestRate;
		this.interestEarned = interestEarned;
	}

	public long getLowerBound() {
		return lowerBound;
	}

	public InterestBand setLowerBound(long lowerBound) {
		this.lowerBound = lowerBound;
		return this;
	}

	public Long getUpperBound() {
		return upperBound;
	}

	public InterestBand setUpperBound(Long upperBound) {
		this.upperBound = upperBound;
		return this;
	}

	/**
	 * As the upper bound can be null, this method returns an actual value which can easily be used in calculation
	 * @return the upper bound value, or Long.MAX_VALUE
	 */
	public long getEffectiveUpperBound() {
		if(upperBound == null) {
			return Long.MAX_VALUE;
		}

		return upperBound;
	}

	public int getInterestRate() {
		return interestRate;
	}

	public InterestBand setInterestRate(int interestRate) {
		this.interestRate = interestRate;
		return this;
	}

	public long getInterestEarned() {
		return interestEarned;
	}

	public InterestBand setInterestEarned(long interestEarned) {
		this.interestEarned = interestEarned;
		return this;
	}

	@Override
	public String toString() {
		return "InterestBand{" +
				"lowerBound=" + lowerBound +
				", upperBound=" + upperBound +
				", interestRate=" + interestRate +
				", interestEarned=" + interestEarned +
				'}';
	}

	@Override
	public int compareTo(InterestBand o) {
		return Long.valueOf(this.getLowerBound()).compareTo(Long.valueOf(o.getLowerBound()));
	}
}
