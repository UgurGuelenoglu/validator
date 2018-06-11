package com.dachser.validator.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dachser.validator.INVENumber;
import com.dachser.validator.NVENumber;

public class TestValidator {

	INVENumber iNEVNumber = new NVENumber();
	NVENumber nveNumber;

	
	@Test
	public void testValidNVENumber() {
		//Given
		String validNVENumber = "001673218762312447";
		
		//when
		boolean checkIfOk = iNEVNumber.checkNVENumber(validNVENumber);
		
		//then
		assertTrue(checkIfOk);
	}
	
	@Test
	public void testInvalidNVENumber() {
		//Given
				String invalidNVENumber = "001673218762312442";
				
				//when
				boolean checkIfOk = iNEVNumber.checkNVENumber(invalidNVENumber);
				
				//then
				assertFalse(checkIfOk);
	}

}
