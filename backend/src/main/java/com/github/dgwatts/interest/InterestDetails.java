package com.github.dgwatts.interest;

public class InterestDetails {
	private Long id;

	private InterestBand[] bands;

	private long totalInterest;

	public Long getId() {
		return id;
	}

	public InterestDetails setId(Long id) {
		this.id = id;
		return this;
	}

	public InterestBand[] getBands() {
		return bands;
	}

	public InterestDetails setBands(InterestBand[] bands) {
		this.bands = bands;
		return this;
	}

	public long getTotalInterest() {
		return totalInterest;
	}

	public InterestDetails setTotalInterest(long totalInterest) {
		this.totalInterest = totalInterest;
		return this;
	}
}
