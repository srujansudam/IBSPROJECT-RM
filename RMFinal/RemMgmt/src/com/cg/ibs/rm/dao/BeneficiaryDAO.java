package com.cg.ibs.rm.dao;

import java.math.BigInteger;
import java.util.Set;

import com.cg.ibs.rm.bean.Beneficiary;
import com.cg.ibs.rm.exception.IBSExceptions;

public interface BeneficiaryDAO {

	public Set<Beneficiary> getDetails(String uci);

	public void copyDetails(String uci, Beneficiary beneficiary)throws IBSExceptions;

	public boolean updateDetails(String uci, Beneficiary beneficiary) throws IBSExceptions;
	
	public boolean deleteDetails(String uci, BigInteger accountNumber) throws IBSExceptions;
	
	public Beneficiary getBeneficiary(String uci, BigInteger accountNumber) throws IBSExceptions;

}
