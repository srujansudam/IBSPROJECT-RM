package com.cg.ibs.rm.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.cg.ibs.rm.bean.AutoPayment;
import com.cg.ibs.rm.bean.Beneficiary;
import com.cg.ibs.rm.bean.CreditCard;
import com.cg.ibs.rm.bean.FinalCustomer;
import com.cg.ibs.rm.bean.ServiceProvider;
import com.cg.ibs.rm.bean.TemporaryCustomer;
import com.cg.ibs.rm.ui.Type;

public class DataStoreImpl {

	private static Map<String, FinalCustomer> finalMap = new HashMap<>();
	private static Map<String, TemporaryCustomer> tempMap = new HashMap<>();
	private static Set<ServiceProvider> providers = new HashSet<>();

	public static Set<ServiceProvider> getProviders() {
		return providers;
	}

	public static Map<String, FinalCustomer> getFinalMap() {
		return finalMap;
	}

	public static Map<String, TemporaryCustomer> getTempMap() {
		return tempMap;
	}

	static {
		FinalCustomer finalCustomer1 = new FinalCustomer();
		AutoPayment autoPayment1 = new AutoPayment();
		AutoPayment autoPayment2 = new AutoPayment();
		autoPayment1.setAmount(new BigDecimal("500"));
		autoPayment1.setDateOfStart("12/12/2019");
		autoPayment1.setServiceProviderId(new BigInteger("1"));
		autoPayment2.setAmount(new BigDecimal("1000"));
		autoPayment2.setDateOfStart("10/1/2020");
		autoPayment2.setServiceProviderId(new BigInteger("2"));
		Set<AutoPayment> savedAutoPaymentServices1 = new HashSet<>();
		savedAutoPaymentServices1.add(autoPayment1);
		savedAutoPaymentServices1.add(autoPayment2);

		CreditCard creditCard1 = new CreditCard();
		creditCard1.setcreditCardNumber(new BigInteger("2351672314250000"));
		creditCard1.setnameOnCreditCard("Shubham Gupta");
		creditCard1.setcreditDateOfExpiry("09/23");
		Set<CreditCard> savedCreditCards1 = new HashSet<>();
		savedCreditCards1.add(creditCard1);

		Beneficiary beneficiary1 = new Beneficiary();
		beneficiary1.setAccountName("Ramesh Ranjan");
		beneficiary1.setAccountNumber(new BigInteger("349825671589"));
		beneficiary1.setBankName("HDFC");
		beneficiary1.setIfscCode("HDFC5478321");
		beneficiary1.setType(Type.SELFINOTHERS);
		Set<Beneficiary> savedBeneficiaries1 = new HashSet<>();
		savedBeneficiaries1.add(beneficiary1);

		finalCustomer1.setUCI("12345");
		finalCustomer1.setCurrentBalance(new BigDecimal("100000"));
		finalCustomer1.setSavedAutoPaymentServices(savedAutoPaymentServices1);
		finalCustomer1.setSavedBeneficiaries(savedBeneficiaries1);
		finalCustomer1.setSavedCreditCards(savedCreditCards1);
		finalMap.put("12345", finalCustomer1);

		FinalCustomer finalCustomer2 = new FinalCustomer();
		AutoPayment autoPayment3 = new AutoPayment();
		autoPayment3.setAmount(new BigDecimal("500"));
		autoPayment3.setDateOfStart("12/01/2020");
		autoPayment3.setServiceProviderId(new BigInteger("3"));
		Set<AutoPayment> savedAutoPaymentServices2 = new HashSet<>();
		savedAutoPaymentServices2.add(autoPayment1);
		savedAutoPaymentServices2.add(autoPayment2);

		CreditCard creditCard2 = new CreditCard();
		CreditCard creditCard3 = new CreditCard();
		creditCard2.setcreditCardNumber(new BigInteger("9281672789250000"));
		creditCard2.setnameOnCreditCard("Shubham Gupta");
		creditCard2.setcreditDateOfExpiry("12/24");
		creditCard3.setcreditCardNumber(new BigInteger("5612345678190000"));
		creditCard3.setnameOnCreditCard("Ayush Kumar");
		creditCard3.setcreditDateOfExpiry("01/21");
		Set<CreditCard> savedCreditCards2 = new HashSet<>();
		savedCreditCards2.add(creditCard2);
		savedCreditCards2.add(creditCard3);

		Beneficiary beneficiary2 = new Beneficiary();
		beneficiary2.setAccountName("Ramesh Ranjan");
		beneficiary2.setAccountNumber(new BigInteger("985825671589"));
		beneficiary2.setBankName("HDFC");
		beneficiary2.setIfscCode("HDFC5486721");
		beneficiary2.setType(Type.OTHERSINOTHERS);
		Set<Beneficiary> savedBeneficiaries2 = new HashSet<>();
		savedBeneficiaries2.add(beneficiary2);

		finalCustomer2.setUCI("123456");
		finalCustomer2.setCurrentBalance(new BigDecimal("10000000"));
		finalCustomer2.setSavedAutoPaymentServices(savedAutoPaymentServices2);
		finalCustomer2.setSavedBeneficiaries(savedBeneficiaries2);
		finalCustomer2.setSavedCreditCards(savedCreditCards2);
		finalMap.put("123456", finalCustomer2);

		TemporaryCustomer tempCustomer = new TemporaryCustomer();
		CreditCard creditCard4 = new CreditCard();
		creditCard4.setcreditCardNumber(new BigInteger("354278945617"));
		creditCard4.setnameOnCreditCard("Shivani Das");
		creditCard4.setcreditDateOfExpiry("10/23");
		Set<CreditCard> unapprovedCreditcards1 = new HashSet<>();
		unapprovedCreditcards1.add(creditCard4);

		Beneficiary beneficiary3 = new Beneficiary();
		beneficiary3.setAccountName("Suraj Lohar");
		beneficiary3.setAccountNumber(new BigInteger("547925479207"));
		beneficiary3.setBankName("IDFC");
		beneficiary3.setIfscCode("IDFC2435612");
		beneficiary3.setType(Type.SELFINOTHERS);
		Set<Beneficiary> savedBeneficiaries3 = new HashSet<>();
		savedBeneficiaries3.add(beneficiary3);
		tempCustomer.setUCI("123456");
		tempCustomer.setUnapprovedBeneficiaries(savedBeneficiaries3);
		tempCustomer.setUnapprovedCreditCards(unapprovedCreditcards1);
		tempMap.put(tempCustomer.getUCI(), tempCustomer);

		ServiceProvider provider1 = new ServiceProvider();
		provider1.setSpi(new BigInteger("1"));
		provider1.setNameOfCompany("Airtel");
		providers.add(provider1);

		ServiceProvider provider2 = new ServiceProvider();
		provider2.setSpi(new BigInteger("2"));
		provider2.setNameOfCompany("Reliance Jio");
		providers.add(provider2);

		ServiceProvider provider3 = new ServiceProvider();
		provider3.setSpi(new BigInteger("3"));
		provider3.setNameOfCompany("Tata Sky");
		providers.add(provider3);

		ServiceProvider provider4 = new ServiceProvider();
		provider4.setSpi(new BigInteger("4"));
		provider4.setNameOfCompany("MTS India");
		providers.add(provider4);
	}
}