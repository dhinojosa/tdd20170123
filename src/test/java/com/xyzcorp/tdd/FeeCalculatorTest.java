package com.xyzcorp.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class FeeCalculatorTest {
	@Test
	public void testFeeSameDate() {
		int penalty = FeeCalculator.calculate(LocalDate.of(2017, 1, 2), 
				                              LocalDate.of(2017, 1, 2), 10);
		assertThat(penalty).isEqualTo(0);
	}

	@Test
	public void testFee1Month0Day() {
		int penalty = FeeCalculator.calculate(LocalDate.of(2017, 1, 2), 
				                              LocalDate.of(2017, 2, 2), 10);
		assertThat(penalty).isEqualTo(0);
	}

	@Test
	public void testFee1Month1Day() {
		int penalty = FeeCalculator.calculate(LocalDate.of(2017, 1, 2), 
				                              LocalDate.of(2017, 2, 3), 10);
		assertThat(penalty).isEqualTo(10);
	}

	@Test
	public void testBackToTheFutureToReturnABookTest() {
		int penalty = FeeCalculator.calculate(LocalDate.of(2017, 7, 3), 
				                              LocalDate.of(2017, 2, 2), 10);
		assertThat(penalty).isEqualTo(0);
	}
	
	@Test
	public void testSanityCheckOneYearInTheFuture() {
		int penalty = FeeCalculator.calculate(LocalDate.of(2017, 1, 3), 
				                              LocalDate.of(2018, 1, 3), 10);
		assertThat(penalty).isEqualTo(110);
	}
	
	@Test
	public void testSanityCheckOneYearAndOneDayInTheFuture() {
		int penalty = FeeCalculator.calculate(LocalDate.of(2017, 1, 3), 
				                              LocalDate.of(2018, 1, 4), 10);
		assertThat(penalty).isEqualTo(120);
	}
}
