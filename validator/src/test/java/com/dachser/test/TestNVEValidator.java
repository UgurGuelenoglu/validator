package com.dachser.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.dachser.validator.INVENumber;
import com.dachser.validator.NVENumber;

class TestNVEValidator {

	
	private INVENumber iNVENumber = new NVENumber();
	
	@Test
	void testNVENumberIsValid() {
		// Given
		String nveNumber = "007189085627231896";

		// When
		boolean valideNVENumber = iNVENumber.checkNVENumber(nveNumber);

		// then
		assertTrue(valideNVENumber);
	}

	@Test
	void testNVENumberIsNotValid() {

		// Given
		String nveNumber = "007189085627231892";

		// When
		boolean valideNVENumber = iNVENumber.checkNVENumber(nveNumber);

		// then
		assertFalse(valideNVENumber);
	}

	@Test
	void testNVENumberWithLetters() {
		// Given
		String nveNumber = "2347980ASDFJ1231";

		// When
		boolean valideNVENumber = iNVENumber.checkNVENumber(nveNumber);

		// then
		assertFalse(valideNVENumber);
	}

}
