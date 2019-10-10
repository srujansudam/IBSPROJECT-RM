package com.cg.ibs.rm.service;

import java.math.BigInteger;
import java.util.Set;

import com.cg.ibs.rm.bean.AutoPayment;
import com.cg.ibs.rm.bean.ServiceProvider;
import com.cg.ibs.rm.exception.IBSExceptions;

public interface AutoPaymentService {
	public Set<ServiceProvider> showIBSServiceProviders();
	
	public Set<AutoPayment> showAutopaymentDetails(String uci);
	
	public boolean autoDeduction(String uci, AutoPayment autoPayment) throws IBSExceptions;
	
	public boolean updateRequirements(String uci, BigInteger spi) throws IBSExceptions;
}
