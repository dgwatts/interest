package com.github.dgwatts.interest;

import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataMongoTest
public class InterestServiceImplTests {

	@Test
	public void testCalculateInterestWithNoBands() {
		fail("not implemented");
	}

	@Test
	public void testCalculateInterestWithOneBand() {
		fail("not implemented");
	}

	@Test
	public void testCalculateInterestWithTwoBands() {
		fail("not implemented");
	}

	@Test
	public void testCalculateInterestWithTwoBandsWhichOverlap() {
		fail("not implemented");
	}

	@Test
	public void testCalculateInterestWithTwoBandsWithAGap() {
		fail("not implemented");
	}

	@Test
	public void testCalculateInterestWithAZeroWidthBand() {
		fail("not implemented");
	}

	@Test
	public void testCalculateInterestWithZeroRateBands() {
		fail("not implemented");
	}

	@Test
	public void testCalculateInterestWithThreeNegativeBands() {
		fail("not implemented");
	}

	@Test
	public void testCalculateInterestWithPositiveZeroAndNegativeBands() {
		fail("not implemented");
	}



}
