package com.xyzcorp.tdd;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.MONTHS;

public class FeeCalculator {

	public static int calculate
	   (LocalDate checkoutDate, LocalDate now, int penalty) {
         if (now.isBefore(checkoutDate)) return 0;		
         return (int) (MONTHS.between(checkoutDate.plusDays(1), now) * penalty);	
	}
}
