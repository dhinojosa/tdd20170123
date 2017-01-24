package com.xyzcorp.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testSingleLineParse() {
		String data = "Janelle Klein~On Intelligence~2014-07-21";
		Parser parser = new Parser();
		Checkout c = parser.parseLine(data);
		assertThat(c.getName()).isEqualTo("Janelle Klein");
		assertThat(c.getDate()).isEqualTo(LocalDate.of(2014, 7, 21));
	}
	
	@Test
	public void testMulipleLineParse() {
		Stream<String> data = Stream.of("Janelle Klein~On Intelligence~2014-07-21",
		          "Jana Nguyen~To Kill a Mockingbird~2016-01-02");
		Parser parser = new Parser();
		Stream<Checkout> checkouts = parser.parseStream(data);
		assertThat(checkouts).hasSize(2);
	}
}
