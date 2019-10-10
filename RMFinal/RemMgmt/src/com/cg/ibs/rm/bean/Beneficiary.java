package com.cg.ibs.rm.bean;

import java.math.BigInteger;

import com.cg.ibs.rm.ui.Type;

public class Beneficiary {
	private BigInteger accountNumber;
	private String accountName;
	private String ifscCode;
	private String bankName;
	private Type type;

	public Beneficiary() {
		super();
	}

	public Beneficiary(BigInteger accountNumber, String accountName, String ifscCode, String bankName, Type type) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.ifscCode = ifscCode;
		this.bankName = bankName;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Beneficiary [accountNumber=" + accountNumber + ", accountName=" + accountName + ", ifscCode=" + ifscCode
				+ ", bankName=" + bankName + ", type=" + type + "]";
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public BigInteger getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(BigInteger accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
