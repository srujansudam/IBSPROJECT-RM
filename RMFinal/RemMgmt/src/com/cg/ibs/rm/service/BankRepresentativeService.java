package com.cg.ibs.rm.service;


import java.util.Set;

import com.cg.ibs.rm.bean.Beneficiary;
import com.cg.ibs.rm.bean.CreditCard;
import com.cg.ibs.rm.exception.IBSExceptions;

public interface BankRepresentativeService {
	public Set<String> showRequests();
	
	public Set<CreditCard> showUnapprovedCreditCards(String uci) throws IBSExceptions;

	public Set<Beneficiary> showUnapprovedBeneficiaries(String uci) throws IBSExceptions;

	public boolean saveCreditCardDetails(String uci, CreditCard card) throws IBSExceptions;

	public void saveBeneficiaryDetails(String uci, Beneficiary beneficiary);

}

