package com.cg.ibs.rm.dao;

import java.util.Map;
import java.util.Set;

import com.cg.ibs.rm.bean.Beneficiary;
import com.cg.ibs.rm.bean.CreditCard;
import com.cg.ibs.rm.bean.FinalCustomer;
import com.cg.ibs.rm.bean.TemporaryCustomer;
import com.cg.ibs.rm.exception.ExceptionMessages;
import com.cg.ibs.rm.exception.IBSExceptions;

public class BankRepresentativeDAOImpl implements BankRepresentativeDAO {

	private Map<String, TemporaryCustomer> tempMap = DataStoreImpl.getTempMap();
	private Map<String, FinalCustomer> finalMap = DataStoreImpl.getFinalMap();

	public Set<String> getRequests() {

		return tempMap.keySet();
	}

	public Set<Beneficiary> getBeneficiaryDetails(String uci) {

		return tempMap.get(uci).getUnapprovedBeneficiaries();
	}

	public Set<CreditCard> getCreditCardDetails(String uci) throws IBSExceptions {
		return tempMap.get(uci).getUnapprovedCreditCards();

	}

	@Override
	public boolean copyCreditCardDetails(String uci, CreditCard card) throws IBSExceptions {
		boolean check = false;
		finalMap.get(uci).getSavedCreditCards().add(card);
		if (finalMap.get(uci).getSavedCreditCards().contains(card)) {
			check = true;
		} else {
			throw new IBSExceptions(ExceptionMessages.ERROR9);
		}

		return check;
	}

	@Override
	public void copyBeneficiaryDetails(String uci, Beneficiary beneficiary) {
		finalMap.get(uci).getSavedBeneficiaries().add(beneficiary);
	}

}
