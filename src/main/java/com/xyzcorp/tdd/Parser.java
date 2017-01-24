package com.xyzcorp.tdd;

import java.io.File;
import java.time.LocalDate;
import java.util.stream.Stream;

public class Parser {

	public Checkout parseLine(String data) {
		String[] array = data.split("~");
		return new Checkout(array[0], LocalDate.parse(array[2]));
	}

	public Stream<Checkout> parseStream(Stream<String> data) {
		return data.map(this::parseLine);
	}

}
