package com.github.dgwatts.interest;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class InterestDetails {

	@Id
	@Field("_id")
	private String id;

	private InterestBand[] bands;

	private long baseAmount;

	private long totalInterest;

	public String getId() {
		return id;
	}

	public InterestDetails setId(String id) {
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

	public long getBaseAmount() {
		return baseAmount;
	}

	public InterestDetails setBaseAmount(long baseAmount) {
		this.baseAmount = baseAmount;
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
