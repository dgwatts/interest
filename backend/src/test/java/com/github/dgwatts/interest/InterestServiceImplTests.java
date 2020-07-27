package com.github.dgwatts.interest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@DataMongoTest = @Autowired MongoTemplate mongoTemplate
@SpringBootTest
@RunWith(SpringRunner.class)
public class InterestServiceImplTests {

	@InjectMocks
	private InterestServiceImpl interestService;

	@Test
	public void testCalculateInterestWithNoBands() {
		final InterestDetails interestDetails = new InterestDetails();
		interestDetails.setBaseAmount(1000);
		interestDetails.setBands(new InterestBand[0]);

		try {
			InterestDetails returned = interestService.doCalculateInterest(interestDetails);
			fail("No bands should cause an exception");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testDoCalculateInterestWithOneBandBounded() {

		final InterestDetails interestDetails = new InterestDetails();

		interestDetails.setBands(new InterestBand[]{
				new InterestBand(0, 1000L, 1)
		});

		// within the bound
		interestDetails.setBaseAmount(500L);
		InterestDetails returned = interestService.doCalculateInterest(interestDetails);
		assertEquals("interest should be as expected", 5, returned.getTotalInterest());

		// at the bound
		interestDetails.setBaseAmount(1000L);
		returned = interestService.doCalculateInterest(interestDetails);
		assertEquals("interest should be as expected", 10, returned.getTotalInterest());

		// over the bound
		interestDetails.setBaseAmount(2000L);
		returned = interestService.doCalculateInterest(interestDetails);
		assertEquals("interest should be as expected", 10, returned.getTotalInterest());
	}

	@Test
	public void testDoCalculateInterestWithOneBandUnbounded() {

		final InterestDetails interestDetails = new InterestDetails();

		interestDetails.setBands(new InterestBand[]{
				new InterestBand(0, null, 1)
		});

		interestDetails.setBaseAmount(500L);
		InterestDetails returned = interestService.doCalculateInterest(interestDetails);
		assertEquals("interest should be as expected", 5, returned.getTotalInterest());

		interestDetails.setBaseAmount(1000L);
		returned = interestService.doCalculateInterest(interestDetails);
		assertEquals("interest should be as expected", 10, returned.getTotalInterest());

		interestDetails.setBaseAmount(2000L);
		returned = interestService.doCalculateInterest(interestDetails);
		assertEquals("interest should be as expected", 20, returned.getTotalInterest());
	}

	@Test
	public void testCalculateInterestWithTwoBandsBounded() {
		final InterestDetails interestDetails = new InterestDetails();
		interestDetails.setBaseAmount(6400L);
		interestDetails.setBands(new InterestBand[]{
				new InterestBand(0, 1000L, 1),
				new InterestBand(1000, 5000L, 2),
		});

		InterestDetails returned = interestService.doCalculateInterest(interestDetails);
		assertEquals("interest should be as expected", 90, returned.getTotalInterest());
	}

	@Test
	public void testCalculateInterestWithTwoBandsUnbunded() {
		final InterestDetails interestDetails = new InterestDetails();
		interestDetails.setBaseAmount(6400L);
		interestDetails.setBands(new InterestBand[]{
				new InterestBand(0, 1000L, 1),
				new InterestBand(1000, null, 2),
		});

		InterestDetails returned = interestService.doCalculateInterest(interestDetails);
		assertEquals("interest should be as expected", 118, returned.getTotalInterest());
	}

	@Test
	public void testCalculateInterestWithThreeBandsFromBrief() {
		final InterestDetails interestDetails = new InterestDetails();
		interestDetails.setBaseAmount(6400L);
		interestDetails.setBands(new InterestBand[]{
				new InterestBand(0, 1000L, 1),
				new InterestBand(1000, 5000L, 2),
				new InterestBand(5000, null, 3)
		});

		InterestDetails returned = interestService.doCalculateInterest(interestDetails);
		assertEquals("interest should be as expected", 132, returned.getTotalInterest());
	}

	@Test
	public void testCalculateInterestWithTwoBandsWhichOverlap() {
		final InterestDetails interestDetails = new InterestDetails();
		interestDetails.setBaseAmount(1000);
		interestDetails.setBands(new InterestBand[]{
				new InterestBand(0, 500L, 1),
				new InterestBand(400, 5000L, 2),
		});

		try {
			InterestDetails returned = interestService.doCalculateInterest(interestDetails);
			fail("Overlapping bands should cause an exception");
		} catch (IllegalArgumentException e) {
		}
	}

	@Test
	public void testCalculateInterestWithTwoBandsWithAGap() {
		final InterestDetails interestDetails = new InterestDetails();
		interestDetails.setBaseAmount(1000);
		interestDetails.setBands(new InterestBand[]{
				new InterestBand(0, 500L, 1),
				new InterestBand(600, 5000L, 2),
		});

		try {
			InterestDetails returned = interestService.doCalculateInterest(interestDetails);
			fail("Gaps between bands should cause an exception");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	public void testCalculateInterestWithAZeroWidthBand() {
		final InterestDetails interestDetails = new InterestDetails();
		interestDetails.setBaseAmount(1000);
		interestDetails.setBands(new InterestBand[]{
				new InterestBand(0, 500L, 1),
				new InterestBand(500L, 500L, 2),
				new InterestBand(500L, 1000L, 2)
		});

		try {
			InterestDetails returned = interestService.doCalculateInterest(interestDetails);
			fail("Zero width bands should cause an exception");
		} catch (IllegalArgumentException e) {

		}	}

	@Test
	public void testCalculateInterestWithTwoBandsWhereOneHasLowerBoundBiggerThanUpper() {
		final InterestDetails interestDetails = new InterestDetails();
		interestDetails.setBaseAmount(1000);
		interestDetails.setBands(new InterestBand[]{
				new InterestBand(0, 500L, 1),
				new InterestBand(5000, 500L, 2),
		});

		try {
			InterestDetails returned = interestService.doCalculateInterest(interestDetails);
			fail("Upper bound must be higher than lower bound");
		} catch (IllegalArgumentException e) {

		}	}

	@Test
	public void testCalculateInterestWithZeroRateBands() {
		final InterestDetails interestDetails = new InterestDetails();
		interestDetails.setBaseAmount(6400L);
		interestDetails.setBands(new InterestBand[]{
				new InterestBand(0, 1000L, 0),
				new InterestBand(1000, 5000L, 0),
				new InterestBand(5000, null, 0)
		});

		InterestDetails returned = interestService.doCalculateInterest(interestDetails);
		assertEquals("interest should be as expected", 0, returned.getTotalInterest());
	}

	@Test
	public void testCalculateInterestWithThreeNegativeBands() {
		final InterestDetails interestDetails = new InterestDetails();
		interestDetails.setBaseAmount(6400L);
		interestDetails.setBands(new InterestBand[]{
				new InterestBand(0, 1000L, -1),
				new InterestBand(1000, 5000L, -2),
				new InterestBand(5000, null, -3)
		});

		InterestDetails returned = interestService.doCalculateInterest(interestDetails);
		assertEquals("interest should be as expected", -132, returned.getTotalInterest());
	}

	@Test
	public void testCalculateInterestWithPositiveZeroAndNegativeBands() {
		final InterestDetails interestDetails = new InterestDetails();
		interestDetails.setBaseAmount(6400L);
		interestDetails.setBands(new InterestBand[]{
				new InterestBand(0, 1000L, 1),
				new InterestBand(1000, 5000L, 0),
				new InterestBand(5000, null, -3)
		});

		InterestDetails returned = interestService.doCalculateInterest(interestDetails);
		assertEquals("interest should be as expected", -32, returned.getTotalInterest());
	}



}
