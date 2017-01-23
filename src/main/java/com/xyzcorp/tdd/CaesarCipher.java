package com.xyzcorp.tdd;

public class CaesarCipher {

	private int shift;

	public CaesarCipher(int shift) {
		this.shift = shift;
	}

	private char convert(char c) {
		if (c < 'A' || c > 'Z')
			return c;
		int charShifted = c + shift - 'A';
		return (char) (charShifted % ('Z' - 'A' + 1) + 'A');
	}

	public String encrypt(String string) {
		if (null == string)
			throw new NullPointerException("String cannot be null");
		if (string.isEmpty())
			return "";
		String result = "";
		for (char c : string.toCharArray()) {
			result += convert(c);
		}
		return result;
	}
}
