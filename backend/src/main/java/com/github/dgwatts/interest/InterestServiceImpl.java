package com.github.dgwatts.interest;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterestServiceImpl implements InterestService {

	private final InterestRepo interestRepo;

	public InterestServiceImpl(@Autowired InterestRepo interestRepo) {
		this.interestRepo = interestRepo;
	}

	@Override
	public InterestDetails calculateInterest(InterestDetails details) {
		return doCalculateInterest(details);
		//return save(details);
	}

	protected InterestDetails doCalculateInterest(InterestDetails details) {

		validateDetails(details);

		long totalInterest = 0;
		for(InterestBand ib : details.getBands()) {
			long amountOfThisBandUsed = -1;

			if(ib.getLowerBound() >= details.getBaseAmount()) {
				// Nothing in this band
				amountOfThisBandUsed = 0;
			} else if(ib.getUpperBound() < details.getBaseAmount()) {
				// all of this band
				amountOfThisBandUsed = ib.getUpperBound() - ib.getLowerBound();
			} else {
				// some of this band
				amountOfThisBandUsed = details.getBaseAmount() - ib.getLowerBound();
			}

			// TODO do this as money calc
			long interestEarnedInThisBand = ((amountOfThisBandUsed * ib.getInterestRate()) / 100);
			ib.setInterestEarned(interestEarnedInThisBand);
			totalInterest += interestEarnedInThisBand;
		}

		details.setTotalInterest(totalInterest);
		return details;
	}

	protected void validateDetails(InterestDetails details) {
		// Validate the bands
		Arrays.sort(details.getBands());
		if(details.getBands().length == 0) {
			throw new InterestException("Must have at least one interest rate band");
		}

		if(details.getBands()[0].getLowerBound() != 0) {
			throw new InterestException("First band must start at 0");
		}

		long lastUpperBound = 0;
		for(InterestBand ib : details.getBands()) {
			if(ib.getLowerBound() >= ib.getUpperBound()) {
				throw new InterestException("Upper bound of a band must be greater than the lower bound");
			}

			if(ib.getLowerBound() < lastUpperBound) {
				// Overlap
				throw new InterestException("Bands must be contiguous, without overlap");
			}

			if(ib.getLowerBound() > lastUpperBound) {
				// Gaps
				throw new InterestException("Bands must be contiguous, without gaps");
			}

			lastUpperBound = ib.getUpperBound();
		}
	}

	@Override
	public InterestDetails[] getHistory() {
		interestRepo.findAll();
		return new InterestDetails[0];
	}

	@Override
	public void clearHistory() {
		interestRepo.deleteAll();
	}

	@Override
	public InterestDetails save(InterestDetails parameters) {
		return interestRepo.save(parameters);
	}
}
