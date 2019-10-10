package com.cg.ibs.rm.bean;


import java.util.Set;
public class TemporaryCustomer {
	private String uci;
	private Set<CreditCard> unapprovedCreditCards;
	private Set<Beneficiary> unapprovedBeneficiaries;

	public TemporaryCustomer() {
		super();
	}

	public TemporaryCustomer(String uci, Set<CreditCard> unapprovedCreditCards,
			Set<Beneficiary> unapprovedBeneficiaries) {
		super();
		this.uci = uci;
		this.unapprovedCreditCards = unapprovedCreditCards;
		this.unapprovedBeneficiaries = unapprovedBeneficiaries;
	}

	@Override
	public String toString() {
		return "TemporaryCustomer [UCI=" + uci + ", unapprovedCreditCards=" + unapprovedCreditCards
				+ ", unapprovedBeneficiaries=" + unapprovedBeneficiaries + "]";
	}

	public String getUCI() {
		return uci;
	}

	public void setUCI(String uci) {
		this.uci = uci;
	}

	public Set<CreditCard> getUnapprovedCreditCards() {
		return unapprovedCreditCards;
	}

	public void setUnapprovedCreditCards(Set<CreditCard> unapprovedCreditCards) {
		this.unapprovedCreditCards = unapprovedCreditCards;
	}

	public Set<Beneficiary> getUnapprovedBeneficiaries() {
		return unapprovedBeneficiaries;
	}

	public void setUnapprovedBeneficiaries(Set<Beneficiary> unapprovedBeneficiaries) {
		this.unapprovedBeneficiaries = unapprovedBeneficiaries;
	}



}
