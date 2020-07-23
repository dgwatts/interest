package com.github.dgwatts.interest;

public class InterestBand {

	private long lowerBound;
	private long upperBound;
	private int interestRate;
	private long interestEarned;

	public long getLowerBound() {
		return lowerBound;
	}

	public InterestBand setLowerBound(long lowerBound) {
		this.lowerBound = lowerBound;
		return this;
	}

	public long getUpperBound() {
		return upperBound;
	}

	public InterestBand setUpperBound(long upperBound) {
		this.upperBound = upperBound;
		return this;
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
}
