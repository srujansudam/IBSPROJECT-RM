package com.cg.ibs.rm.service;

import java.math.BigInteger;
import java.util.Set;

import com.cg.ibs.rm.bean.Beneficiary;
import com.cg.ibs.rm.exception.IBSExceptions;

public interface BeneficiaryAccountService {
	public Set<Beneficiary> showBeneficiaryAccount(String uci);

	public boolean validateBeneficiaryAccountNumber(BigInteger accountNumber);

	public boolean validateBeneficiaryAccountNameOrBankName(String name);

	public boolean validateBeneficiaryIfscCode(String Ifsc);

	public boolean modifyBeneficiaryAccountDetails( String uci, BigInteger accountNumber, Beneficiary beneficiary)
			throws IBSExceptions;

	public boolean deleteBeneficiaryAccountDetails(String uci, BigInteger accountNumber) throws IBSExceptions;

	public boolean saveBeneficiaryAccountDetails(String uci, Beneficiary beneficiary) throws IBSExceptions;
}
