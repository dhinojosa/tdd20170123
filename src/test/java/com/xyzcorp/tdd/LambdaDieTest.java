package com.xyzcorp.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LambdaDieTest {

	@Test
	@Category(value = UnitTest.class)
	public void testSimpleRollOf4WithFunction() {

		// Die is subject under test
		LambdaDie die = new LambdaDie(() -> 4);
		Die rolledDie = die.roll();
		assertThat(rolledDie.getPips()).isEqualTo(4);
	}
	
	@Test
	@Category(value = UnitTest.class)
	public void testSimpleRollOf4Integration() {
		// Die is subject under test
		Random random = new Random();
		LambdaDie die = new LambdaDie(() -> random.nextInt(6) + 1);
		Die rolledDie = die.roll();
		assertThat(rolledDie.getPips()).isEqualTo(4);
	}
}



