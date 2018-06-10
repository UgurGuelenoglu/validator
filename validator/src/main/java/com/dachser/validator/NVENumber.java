package com.dachser.validator;

public class NVENumber implements INVENumber{

	private String nveNumber;

	public NVENumber() {

	}

	public String getNveNumber() {
		return nveNumber;
	}

	public void setNveNumber(String nveNumber) {
		this.nveNumber = nveNumber;
	}

	/**
	 * This method checks the input NVE Number if it is valid or not
	 * 
	 * @param nveNumber
	 * @return true if nve is valid or false if not valid
	 */
	public boolean checkNVENumber(String nveNumber) {
		
		char nveNumberArray[] = nveNumber.toCharArray();
		int sum = 0;
		boolean switchMultiplicator = true;
		
		for (int i = nveNumberArray.length - 2; i >= 0; i--) {
			int currentNumber = Character.getNumericValue(nveNumberArray[i]);
			if (switchMultiplicator) {
				sum += currentNumber * 3;
				switchMultiplicator = false;
			} else {
				sum += currentNumber * 1;
				switchMultiplicator = true;
			}
		}
		
		int checkDigit = 0;
		
		if ((sum / 1) % 10 > 0) {
			checkDigit = 10 - (sum / 1) % 10;
		} else {
			checkDigit = 0;
		}

		if (checkDigit == Character.getNumericValue(nveNumberArray[nveNumber.length() - 1])) {
			return true;
		} else {
			return false;
		}

	}
	
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		
		if(obj instanceof NVENumber) {
			isEqual = (this.nveNumber.equals(((NVENumber)obj).nveNumber));
		}
			
		
		
		return isEqual;
	}

}
