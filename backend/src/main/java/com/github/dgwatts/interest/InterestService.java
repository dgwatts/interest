package com.github.dgwatts.interest;

public interface InterestService {
	int calculateInterest();

	InterestCalculationParameters[] getHistory();

	void clearHistory();
}
