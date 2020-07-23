package com.github.dgwatts.interest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/interest")
public class InterestController {

	private InterestService interestService;

	public InterestController(@Autowired InterestService interestService) {
		this.interestService = interestService;
	}

	@RequestMapping(value = "/calculate", consumes = "application/json", method = RequestMethod.POST)
	public int calculateInterest(InterestDetails parameters) {
		return interestService.calculateInterest(parameters);
	}

	@RequestMapping(value = "/persist", consumes = "application/json", method = RequestMethod.POST)
	public InterestDetails save(InterestDetails parameters) {
		return interestService.save(parameters);
	}

	@RequestMapping(value = "/history", method = RequestMethod.GET)
	public InterestDetails[] getHistory() {

		return interestService.getHistory();
	}

	@RequestMapping(value = "/history", method = RequestMethod.DELETE)
	public void clearHistory() {
		interestService.clearHistory();
	}
}
