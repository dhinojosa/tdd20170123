package com.xyzcorp.tdd;

import java.time.LocalDate;

public class Checkout {

	private String name;
	private LocalDate date;

	public Checkout(String name, LocalDate date) {
		this.name = name;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDate() {
		return date;
	}

}
