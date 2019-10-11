package com.cg.ibs.rm.dao;


import java.util.Set;
import com.cg.ibs.rm.bean.Beneficiary;
import com.cg.ibs.rm.bean.CreditCard;
import com.cg.ibs.rm.exception.IBSExceptions;

public interface BankRepresentativeDAO {
	public Set<String> getRequests();

	public Set<CreditCard> getCreditCardDetails(String uci) throws IBSExceptions;

	public Set<Beneficiary> getBeneficiaryDetails(String uci) throws IBSExceptions;

	public boolean copyCreditCardDetails(String uci, CreditCard card) throws IBSExceptions;

	public void copyBeneficiaryDetails(String uci, Beneficiary beneficiary);

}
