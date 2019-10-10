package com.cg.ibs.rm.bean;

import java.math.BigInteger;

public class CreditCard {
	private BigInteger creditCardNumber;
	private String nameOnCreditCard;
	private String creditDateOfExpiry;

	public CreditCard() {
		super();
	}

	public CreditCard(BigInteger creditCardNumber, String nameOnCreditCard, String creditDateOfExpiry) {
		super();
		this.creditCardNumber = creditCardNumber;
		this.nameOnCreditCard = nameOnCreditCard;
		this.creditDateOfExpiry = creditDateOfExpiry;
	}

	@Override
	public String toString() {
		return "CreditCard [creditCardNumber=" + creditCardNumber + ", nameOnCreditCard=" + nameOnCreditCard
				+ ", creditDateOfExpiry=" + creditDateOfExpiry + "]";
	}

	public BigInteger getcreditCardNumber() {
		return creditCardNumber;
	}

	public void setcreditCardNumber(BigInteger creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getnameOnCreditCard() {
		return nameOnCreditCard;
	}

	public void setnameOnCreditCard(String nameOnCreditCard) {
		this.nameOnCreditCard = nameOnCreditCard;
	}

	public String getcreditDateOfExpiry() {
		return creditDateOfExpiry;
	}

	public void setcreditDateOfExpiry(String creditDateOfExpiry) {
		this.creditDateOfExpiry = creditDateOfExpiry;
	}

}
