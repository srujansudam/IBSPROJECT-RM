package com.cg.ibs.rm.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import com.cg.ibs.rm.bean.AutoPayment;
import com.cg.ibs.rm.bean.FinalCustomer;
import com.cg.ibs.rm.bean.ServiceProvider;
import com.cg.ibs.rm.exception.ExceptionMessages;
import com.cg.ibs.rm.exception.IBSExceptions;

public class AutoPaymentDAOImpl implements AutoPaymentDAO {

	private Map<String, FinalCustomer> finalMap = DataStoreImpl.getFinalMap();
	private Set<ServiceProvider> providers = DataStoreImpl.getProviders();
	private Iterator<AutoPayment> it;

	@Override
	public Set<AutoPayment> getAutopaymentDetails(String uci) {
		return finalMap.get(uci).getSavedAutoPaymentServices();

	}

	@Override
	public void copyDetails(String uci, AutoPayment autoPayment) {
		finalMap.get(uci).getSavedAutoPaymentServices().add(autoPayment);

	}

	@Override
	public boolean deleteDetails(String uci, BigInteger serviceProviderId) throws IBSExceptions {
		boolean result = false;
		int count = 0;
		for (AutoPayment autoPayment : finalMap.get(uci).getSavedAutoPaymentServices()) {
			if (autoPayment.getServiceProviderId().equals(serviceProviderId)) {
				count++;
			}
		}
		if (0 == count) {
			throw new IBSExceptions(ExceptionMessages.ERROR6);
			
		} else {
			it = finalMap.get(uci).getSavedAutoPaymentServices().iterator();
			while (it.hasNext()) {
				AutoPayment autoPayment = it.next();
				if (autoPayment.getServiceProviderId().equals(serviceProviderId)) {
					it.remove();
					result = true;
				}
			}
			return result;
		}
	}

	public Set<ServiceProvider> showServiceProviderList() {
		return providers;
	}

	@Override
	public BigDecimal getCurrentBalance(String uci) {
		return finalMap.get(uci).getCurrentBalance();

	}

	@Override
	public void setCurrentBalance(String uci, BigDecimal currentBalnce) {
		finalMap.get(uci).setCurrentBalance(currentBalnce);

	}

}
