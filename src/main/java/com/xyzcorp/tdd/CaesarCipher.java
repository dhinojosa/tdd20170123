package com.xyzcorp.tdd;

import java.util.Objects;
import java.util.stream.Collectors;

public class CaesarCipher {

	private int defaultShift;

	public CaesarCipher(int defaultShift) {
		this.defaultShift = defaultShift;
	}
	
	private int convert(int c, int shift) {
		if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) return c;
	
		int offset = 'A';
		if (Character.isLowerCase(c)) {
			offset = 'a';
		}
		
		int charShifted = c + shift - offset;
		int alphaLen = 'Z' - 'A' + 1;
		return (charShifted % alphaLen + alphaLen) % alphaLen + offset;
	}
	
	private String helper(String string, int shift) {
		Objects.requireNonNull(string, "String cannot be null");
		return string.chars().boxed()
				.map(i -> "" + (char) convert(i, shift))
				.collect(Collectors.joining());
	}

	public String encrypt(String string) {
        return helper(string, defaultShift);
	}

	public String decrypt(String string) {
		return helper(string, -defaultShift);
	}
}
