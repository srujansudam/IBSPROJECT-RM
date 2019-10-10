package com.cg.ibs.rm.bean;

import java.math.BigDecimal;
import java.util.Set;

public class FinalCustomer {
	private String uci;
	private Set<CreditCard> savedCreditCards;
	private Set<AutoPayment> savedAutoPaymentServices;
	private Set<Beneficiary> savedBeneficiaries;
	private BigDecimal currentBalance;

	public FinalCustomer() {
		super();
	}

	@Override
	public String toString() {
		return "Customer [UCI=" + uci + ", savedCreditCards=" + savedCreditCards + ", savedAutoPaymentServices="
				+ savedAutoPaymentServices + ", savedBeneficiaries=" + savedBeneficiaries + ", currentBalance="
				+ currentBalance + "]";
	}

	public FinalCustomer(String uCI, Set<CreditCard> savedCreditCards, Set<AutoPayment> savedAutoPaymentServices,
			Set<Beneficiary> savedBeneficiaries, BigDecimal currentBalance) {
		super();
		uci = uCI;
		this.savedCreditCards = savedCreditCards;
		this.savedAutoPaymentServices = savedAutoPaymentServices;
		this.savedBeneficiaries = savedBeneficiaries;
		this.currentBalance = currentBalance;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getUCI() {
		return uci;
	}

	public void setUCI(String uCI) {
		uci = uCI;
	}

	public Set<CreditCard> getSavedCreditCards() {
		return savedCreditCards;
	}

	public void setSavedCreditCards(Set<CreditCard> savedCreditCards) {
		this.savedCreditCards = savedCreditCards;
	}

	public Set<AutoPayment> getSavedAutoPaymentServices() {
		return savedAutoPaymentServices;
	}

	public void setSavedAutoPaymentServices(Set<AutoPayment> savedAutoPaymentServices) {
		this.savedAutoPaymentServices = savedAutoPaymentServices;
	}

	public Set<Beneficiary> getSavedBeneficiaries() {
		return savedBeneficiaries;
	}

	public void setSavedBeneficiaries(Set<Beneficiary> savedBeneficiaries) {
		this.savedBeneficiaries = savedBeneficiaries;
	}

}
