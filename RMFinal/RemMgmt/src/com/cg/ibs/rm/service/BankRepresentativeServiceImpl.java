package com.cg.ibs.rm.service;

import java.util.Set;

import com.cg.ibs.rm.bean.Beneficiary;
import com.cg.ibs.rm.bean.CreditCard;
import com.cg.ibs.rm.dao.BankRepresentativeDAO;
import com.cg.ibs.rm.dao.BankRepresentativeDAOImpl;
import com.cg.ibs.rm.exception.IBSExceptions;

public class BankRepresentativeServiceImpl implements BankRepresentativeService {
	private BankRepresentativeDAO bankRepresentativeDAO = new BankRepresentativeDAOImpl();

	@Override
	public Set<String> showRequests() {
		return bankRepresentativeDAO.getRequests();

	}

	@Override
	public Set<CreditCard> showUnapprovedCreditCards(String uci) throws IBSExceptions {
		return bankRepresentativeDAO.getCreditCardDetails(uci);

	}

	@Override
	public Set<Beneficiary> showUnapprovedBeneficiaries(String uci) throws IBSExceptions {
		return bankRepresentativeDAO.getBeneficiaryDetails(uci);
		}
	
	public boolean saveCreditCardDetails(String uci, CreditCard card) throws IBSExceptions {
		boolean check= false;
		check =bankRepresentativeDAO.copyCreditCardDetails(uci, card);
		return check;
	}

	@Override
	public void saveBeneficiaryDetails(String uci, Beneficiary beneficiary) {
		bankRepresentativeDAO.copyBeneficiaryDetails(uci, beneficiary);
	}

}
