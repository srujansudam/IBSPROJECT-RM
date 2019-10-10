package com.cg.ibs.rm.dao;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.cg.ibs.rm.bean.Beneficiary;
import com.cg.ibs.rm.bean.FinalCustomer;
import com.cg.ibs.rm.bean.TemporaryCustomer;
import com.cg.ibs.rm.exception.ExceptionMessages;
import com.cg.ibs.rm.exception.IBSExceptions;

public class BeneficiaryDAOImpl implements BeneficiaryDAO {
	private Map<String, TemporaryCustomer> tempMap = DataStoreImpl.getTempMap();
	private Map<String, FinalCustomer> finalMap = DataStoreImpl.getFinalMap();
	private Iterator<Beneficiary> it;

	public Set<Beneficiary> getDetails(String uci) {
		return finalMap.get(uci).getSavedBeneficiaries();

	}

	@Override
	public void copyDetails(String uci, Beneficiary beneficiary) throws IBSExceptions {
		if (finalMap.get(uci).getSavedBeneficiaries().contains(beneficiary)) {
			throw new IBSExceptions(ExceptionMessages.ERROR3);
		} else {
			tempMap.get(uci).getUnapprovedBeneficiaries().add(beneficiary);
		}

	}

	public Beneficiary getBeneficiary(String uci, BigInteger accountNumber) {
		Beneficiary beneficiary1 = null;
		it = finalMap.get(uci).getSavedBeneficiaries().iterator();
		while (it.hasNext()) {
			Beneficiary beneficiary = it.next();
			if (beneficiary.getAccountNumber().equals(accountNumber)) {
				beneficiary1 = beneficiary;
			}
		}
		return beneficiary1;
	}

	@Override
	public boolean updateDetails(String uci, Beneficiary beneficiary1) throws IBSExceptions {

		boolean result = false;
			if (!(finalMap.get(uci).getSavedBeneficiaries().contains(beneficiary1))) {
				throw new IBSExceptions(ExceptionMessages.ERROR4);

			}
			it = finalMap.get(uci).getSavedBeneficiaries().iterator();
			while (it.hasNext()) {
				Beneficiary beneficiary = it.next();
				if (beneficiary.getAccountNumber().equals(beneficiary1.getAccountNumber())) {
					it.remove();
					result = true;
				}
			}
		
		tempMap.get(uci).getUnapprovedBeneficiaries().add(beneficiary1);
		return result;
	}

	@Override
	public boolean deleteDetails(String uci, BigInteger accountNumber) throws IBSExceptions {
		boolean result = false;
		int count = 0;
		for (Beneficiary beneficiary : finalMap.get(uci).getSavedBeneficiaries()) {
			if (beneficiary.getAccountNumber().equals(accountNumber)) {
				count++;
			}
		}
		if (0 == count) {
			throw new IBSExceptions(ExceptionMessages.ERROR4);
		} else {
			it = finalMap.get(uci).getSavedBeneficiaries().iterator();
			while (it.hasNext()) {
				Beneficiary beneficiary = it.next();
				if (beneficiary.getAccountNumber().equals(accountNumber)) {
					it.remove();
					result = true;
				}
			}
		}
		return result;
	}

}
