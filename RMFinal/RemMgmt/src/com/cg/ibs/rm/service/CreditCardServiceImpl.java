package com.cg.ibs.rm.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.regex.Pattern;

import com.cg.ibs.rm.bean.CreditCard;
import com.cg.ibs.rm.dao.CreditCardDAO;
import com.cg.ibs.rm.dao.CreditCardDAOImpl;
import com.cg.ibs.rm.exception.ExceptionMessages;
import com.cg.ibs.rm.exception.IBSExceptions;

public class CreditCardServiceImpl implements CreditCardService {

	private CreditCardDAO creditCardDao = new CreditCardDAOImpl();
	
	@Override
	public Set<CreditCard> showCardDetails(String uci) {
		return creditCardDao.getDetails(uci);
		 
	}

	@Override
	public boolean validateCardNumber(BigInteger creditCardNumber) {
		boolean validNumber = true;
		if (creditCardNumber.compareTo(new BigInteger("999999999999999")) == -1
				|| creditCardNumber.compareTo(new BigInteger("10000000000000000")) == 1)
			validNumber = false;
		return validNumber;
	}

	@Override
	public boolean validateDateOfExpiry(String creditDateOfExpiry) throws IBSExceptions {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if (!Pattern.matches("^(((3[0 1])|([1 2][0-9])|(0[1-9]))//((1[0-2])|(0[1-9]))//[0-9]{4})$",creditDateOfExpiry))
		{
			throw new IBSExceptions(ExceptionMessages.ERROR7);
		}
		LocalDate creditCardExpiry = LocalDate.parse(creditDateOfExpiry, formatter);
		boolean validDate = true;
		if (creditCardExpiry.isBefore(today))
			validDate = false;
		return validDate;
	}

	@Override
	public boolean validateNameOnCard(String nameOnCreditCard) {
		boolean validName = false;
		if (Pattern.matches("^[a-zA-Z]*$", nameOnCreditCard) && (nameOnCreditCard != null))
			validName = true;
		return validName;
	}

	@Override
	public boolean deleteCardDetails(String uci, BigInteger creditCardNumber) throws IBSExceptions {
		return creditCardDao.deleteDetails(uci, creditCardNumber);
	}

	@Override
	public void saveCardDetails(String uci, CreditCard card) throws IBSExceptions {
		creditCardDao.copyDetails(uci, card);

	}
}
