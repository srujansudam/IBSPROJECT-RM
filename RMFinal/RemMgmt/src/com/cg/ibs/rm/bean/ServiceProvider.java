package com.cg.ibs.rm.bean;

import java.math.BigInteger;

public class ServiceProvider {
	private BigInteger spi;
	private String nameOfCompany;

	public ServiceProvider() {
		super();
	}

	public ServiceProvider(BigInteger spi, String nameOfCompany) {
		super();
		this.spi = spi;
		this.nameOfCompany = nameOfCompany;
	}

	@Override
	public String toString() {
		return "ServiceProvider [spi=" + spi + ", nameOfCompany=" + nameOfCompany + "]";
	}

	public BigInteger getSpi() {
		return spi;
	}

	public void setSpi(BigInteger spi) {
		this.spi = spi;
	}

	public String getNameOfCompany() {
		return nameOfCompany;
	}

	public void setNameOfCompany(String nameOfCompany) {
		this.nameOfCompany = nameOfCompany;
	}

}
