package com.xyzcorp.tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.easymock.internal.matchers.Null;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CaesarCipherTest {

	// 1. Is there existing class that this should belong to?
	// 2. Do I want to create it static method?
	// 3. Do I want to create or instance method?
	// 4. Find the simplest thing to test.

	@Test
	public void testEncryptionEmptyString() {
		// 1. CaesarCipher cc = new CaesarCipher();
		// assertEquals(cc.encrypt(""), "");
		// 2. CaesarCipher cc = new CaesarCipher(5);
		// assertEquals(cc.encrypt(""), "");
		// 3. String result = CaesarCipher.encrypt("", 4)
		// assertEquals(result, "");
		
		CaesarCipher cc = new CaesarCipher(5);
		assertEquals("", cc.encrypt(""));
	}

	@Test
	public void testEncryptionWithANull() {
		CaesarCipher cc = new CaesarCipher(5);
		try {
		   cc.encrypt(null);
		   fail("Expected a failure here");
		} catch(NullPointerException npe) {
		   assertEquals("String cannot be null", 
				   npe.getMessage());
		}
	}

	//Not Preferred
	@Test(expected = NullPointerException.class)
	public void testEncryptionWithANullAnnotation() {
		CaesarCipher cc = new CaesarCipher(5);
		cc.encrypt(null);
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();


	@Test
	public void testEncryptionWithANullRule() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage("String cannot be null");
		CaesarCipher cc = new CaesarCipher(5);
		cc.encrypt(null);
	}

	@Test
	public void testEncryptWithShift0AndStringOneItem() {
		CaesarCipher cc = new CaesarCipher(0);
		assertEquals("A", cc.encrypt("A"));
	}
	
	
	@Test
	public void testEncryptWithShift1AndStringOneItem() {
		CaesarCipher cc = new CaesarCipher(1);
		assertEquals("C", cc.encrypt("B"));
	}
	
	//NonRedBar
	@Test
	public void testEncryptWithShift2AndStringOneItem() {
		CaesarCipher cc = new CaesarCipher(2);
		assertEquals("D", cc.encrypt("B"));
	}
	
	@Test
	public void testEncryptWithShift2AndStringOneItemBeingAZ() {
		CaesarCipher cc = new CaesarCipher(2);
		assertEquals("B", cc.encrypt("Z"));
	}
	
	@Test
	public void testEncryptWithFiveLetterWordAndShiftOf2() {
		CaesarCipher cc = new CaesarCipher(2);
		assertEquals("BGDTC", cc.encrypt("ZEBRA"));
	}
	
	@Test
	public void testEncryptWithTwoWordsAndShiftOf2() {
		CaesarCipher cc = new CaesarCipher(2);
		assertEquals("BGDTC UVTKRG", cc.encrypt("ZEBRA STRIPE"));
	}
	
	public void testWithDashesAndQuotes() {
		
	}
	//???
	public void testNegativeShift() {}
}
