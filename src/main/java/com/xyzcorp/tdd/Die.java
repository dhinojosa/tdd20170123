package com.xyzcorp.tdd;

import java.util.Objects;
import java.util.Random;

public class Die {
    private final int pips;
    private final Random random;

    protected Die(Random random) {
        this(random, 1);
    }

    protected Die(Random random, int pips) {
    	    Objects.requireNonNull(random, "Random cannot be null");
        this.random = random;
        this.pips = pips;
    }

    public int getPips() {
        return pips;
    }

    public Die roll() {
        return new Die(random, random.nextInt(6) + 1);
    }

	public static Die createStandardDie() {
		return new Die(new Random());
	}
}
