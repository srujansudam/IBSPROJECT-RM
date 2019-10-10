package com.cg.ibs.rm.bean;

import java.math.BigDecimal;
import java.math.BigInteger;

public class AutoPayment {
	private BigDecimal amount;
	private String dateOfStart;
	private BigInteger serviceProviderId;

	public AutoPayment() {
		super();
	}

	public AutoPayment(BigDecimal amount, String dateOfStart, BigInteger serviceProviderId) {
		super();
		this.amount = amount;
		this.dateOfStart = dateOfStart;
		this.serviceProviderId = serviceProviderId;
	}

	@Override
	public String toString() {
		return "AutoPayment [amount=" + amount + ", dateOfStart=" + dateOfStart  + ", serviceProviderId=" + serviceProviderId + "]";
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDateOfStart() {
		return dateOfStart;
	}

	public void setDateOfStart(String dateOfStart) {
		this.dateOfStart = dateOfStart;
	}

	public BigInteger getServiceProviderId() {
		return serviceProviderId;
	}

	public void setServiceProviderId(BigInteger serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

}
