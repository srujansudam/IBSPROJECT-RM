package com.cg.ibs.rm.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.regex.Pattern;

import com.cg.ibs.rm.bean.AutoPayment;
import com.cg.ibs.rm.bean.ServiceProvider;
import com.cg.ibs.rm.dao.AutoPaymentDAO;
import com.cg.ibs.rm.dao.AutoPaymentDAOImpl;
import com.cg.ibs.rm.exception.ExceptionMessages;
import com.cg.ibs.rm.exception.IBSExceptions;

public class AutoPaymentServiceImpl implements AutoPaymentService {
	private AutoPaymentDAO autoPaymentDao = new AutoPaymentDAOImpl();

	@Override
	public Set<AutoPayment> showAutopaymentDetails(String uci) {
		return autoPaymentDao.getAutopaymentDetails(uci);

	}

	@Override
	public Set<ServiceProvider> showIBSServiceProviders() {
		return autoPaymentDao.showServiceProviderList();

	}

	@Override
	public boolean autoDeduction(String uci, AutoPayment autoPayment) throws IBSExceptions {
		LocalDate today = LocalDate.now();
		if (!Pattern.matches("^(((3[0 1])|([1 2][0-9])|(0[1-9]))//((1[0-2])|(0[1-9]))//[0-9]{4})$", autoPayment.getDateOfStart()))
				throw new IBSExceptions(ExceptionMessages.ERROR7);	
		boolean validAutoDeduct = false;
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate startOfAutoPayment = LocalDate.parse(autoPayment.getDateOfStart(), dtFormatter);
		if (startOfAutoPayment.isBefore(today)) {

			throw new IBSExceptions(ExceptionMessages.ERROR7);
		} else {
			autoPaymentDao.copyDetails(uci, autoPayment);



			if (today.equals(startOfAutoPayment)) {
				if (-1 != autoPaymentDao.getCurrentBalance(uci).compareTo(autoPayment.getAmount())) {
					BigDecimal balance = autoPaymentDao.getCurrentBalance(uci).subtract(autoPayment.getAmount());
					autoPaymentDao.setCurrentBalance(uci, balance);
					startOfAutoPayment.plusMonths(1);
				}
			}
			validAutoDeduct = true;
		}


		return validAutoDeduct;
	}

	@Override
	public boolean updateRequirements(String uci, BigInteger spi) throws IBSExceptions {
		return autoPaymentDao.deleteDetails(uci, spi);
	}

}
