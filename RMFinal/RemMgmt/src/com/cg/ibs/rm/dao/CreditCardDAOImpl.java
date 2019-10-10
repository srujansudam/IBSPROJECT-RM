package com.cg.ibs.rm.dao;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.cg.ibs.rm.bean.CreditCard;
import com.cg.ibs.rm.bean.FinalCustomer;
import com.cg.ibs.rm.bean.TemporaryCustomer;
import com.cg.ibs.rm.exception.ExceptionMessages;
import com.cg.ibs.rm.exception.IBSExceptions;

public class CreditCardDAOImpl implements CreditCardDAO {

	private Map<String, TemporaryCustomer> tempMap = DataStoreImpl.getTempMap();
	private Map<String, FinalCustomer> finalMap = DataStoreImpl.getFinalMap();
	private Iterator<CreditCard> it;

	@Override
	public Set<CreditCard> getDetails(String uci) {
		return finalMap.get(uci).getSavedCreditCards();
	}

	@Override
	public void copyDetails(String uci, CreditCard card) throws IBSExceptions {
		if (finalMap.get(uci).getSavedCreditCards().contains(card)) {
			throw new IBSExceptions(ExceptionMessages.ERROR1);
		} else {
			tempMap.get(uci).getUnapprovedCreditCards().add(card);
		}
	}

	@Override
	public boolean deleteDetails(String uci, BigInteger cardNumber) throws IBSExceptions {
		boolean result = false;

		int count = 0;
		for (CreditCard card : finalMap.get(uci).getSavedCreditCards()) {
			if (card.getcreditCardNumber().equals(cardNumber)) {
				count++;
			}
		}
		if (0 == count) {
			throw new IBSExceptions(ExceptionMessages.ERROR2);
		} else {
			it = finalMap.get(uci).getSavedCreditCards().iterator();
			while (it.hasNext()) {
				CreditCard card = it.next();
				if (card.getcreditCardNumber().equals(cardNumber)) {
					it.remove();
					result = true;
				}
			}
			return result;
		}
	}

}
