package com.github.dgwatts.interest;

public interface InterestService {
	InterestDetails calculateInterest(InterestDetails parameters);

	InterestDetails[] getHistory();

	void clearHistory();

	InterestDetails save(InterestDetails parameters);
}
