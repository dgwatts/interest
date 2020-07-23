package com.github.dgwatts.interest;

public interface InterestService {
	InterestCalculationParameters calculateInterest(InterestCalculationParameters parameters);

	InterestCalculationParameters[] getHistory();

	void clearHistory();

	InterestCalculationParameters save(InterestCalculationParameters parameters);
}
