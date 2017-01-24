package com.xyzcorp.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.mockito.Mockito;

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
	
	@Test
	public void testIntegrationWithClasspath() {
		InputStream is = getClass().getResourceAsStream("/library.txt");
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader buff = new BufferedReader(reader);
		Parser parser = new Parser();
		Stream<Checkout> checkouts = parser.parseStream(buff.lines());
		assertThat(checkouts).hasSize(16);
	}
	
	@Test
	public void testWithURLWebService() throws IOException {
		URL url = 
		new URL("https://raw.githubusercontent.com/dhinojosa/tdd20170123/master/src/main/resources/library.txt");
		InputStream is = url.openConnection().getInputStream();
		InputStreamReader reader = new InputStreamReader(is);
		BufferedReader buff = new BufferedReader(reader);
		Parser parser = new Parser();
		Stream<Checkout> checkouts = parser.parseStream(buff.lines());
		assertThat(checkouts).hasSize(16);
	}
	
	@Test
	public void testMulipleLineParseWithMockBufferedReader() throws IOException {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
		Mockito.when(reader.readLine())
		  .thenReturn("Janelle Klein~On Intelligence~2014-07-21",
				      "Jana Nguyen~To Kill a Mockingbird~2016-01-02");
		          
		//Parser parser = new Parser();
		//Stream<Checkout> checkouts = parser.parseStream(data);
		//assertThat(checkouts).hasSize(2);
	}

	
	//TODO: Sink your code! This is not done yet.
	// 1. Blank Line
	// 2. Too many/too few fields
	// 3. Date in the future
	
	
}
