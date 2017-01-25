package com.xyzcorp.tdd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LibraryRunner {

	public static void main(String[] args) {
		InputStream is = LibraryRunner.class.getResourceAsStream("/library.txt");
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader buff = new BufferedReader(reader);
		Parser parser = new Parser();
		Stream<Checkout> checkouts = parser.parseStream(buff.lines());
		checkouts.parallel()
		        .sorted(Comparator.comparing(Checkout::getDate))
				.limit(5)
				.forEach(c -> System.out.println(c.getName() + ":" + 
				   FeeCalculator.calculate(c.getDate(), LocalDate.now(), 10)));
	}

}
