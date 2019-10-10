package com.cg.ibs.rm.dao;

import java.math.BigInteger;
import java.util.Set;

import com.cg.ibs.rm.bean.CreditCard;
import com.cg.ibs.rm.exception.IBSExceptions;

public interface CreditCardDAO {
	public Set<CreditCard> getDetails(String uci);

	public void copyDetails(String uci, CreditCard card) throws IBSExceptions;

	public boolean deleteDetails(String uci, BigInteger cardNumber) throws IBSExceptions;
}
