package com.cg.ibs.rm.service;

import java.math.BigInteger;
import java.util.Set;
import java.util.regex.Pattern;

import com.cg.ibs.rm.bean.Beneficiary;
import com.cg.ibs.rm.dao.BeneficiaryDAO;
import com.cg.ibs.rm.dao.BeneficiaryDAOImpl;
import com.cg.ibs.rm.exception.IBSExceptions;

public class BeneficiaryAccountServiceImpl implements BeneficiaryAccountService {

	private BeneficiaryDAO beneficiaryDao = new BeneficiaryDAOImpl();
	private Beneficiary beneficiary = new Beneficiary();
	

	@Override
	public Set<Beneficiary> showBeneficiaryAccount(String uci) {
		return beneficiaryDao.getDetails(uci);
		
	}

	@Override
	public boolean validateBeneficiaryAccountNumber(BigInteger accountNumber) {
		boolean validNumber = true;
		if (accountNumber.compareTo(new BigInteger("99999999999")) == -1
				|| accountNumber.compareTo(new BigInteger("10000000000000")) == 1)
			validNumber = false;
		return validNumber;
	}

	@Override
	public boolean validateBeneficiaryAccountNameOrBankName(String name) {
		boolean validName = false;
		if (Pattern.matches("^[a-zA-Z]*$", name) && (null != name))
			validName = true;
		return validName;
	}

	@Override
	public boolean validateBeneficiaryIfscCode(String ifsc) {
		boolean validIfsc = false;
		if (ifsc.length() == 11)
			validIfsc = true;
		return validIfsc;
	}

	@Override
	public boolean modifyBeneficiaryAccountDetails(String uci, BigInteger accountNumber, Beneficiary beneficiary1)
			throws IBSExceptions {
		beneficiary = beneficiaryDao.getBeneficiary(uci, accountNumber);
		boolean validModify = false;
		if(beneficiary1.getAccountName() != null){
			beneficiary.setAccountName(beneficiary1.getAccountName());
		}
		if(beneficiary1.getIfscCode() != null){
			beneficiary.setIfscCode(beneficiary1.getIfscCode());
		}
		if(beneficiary1.getBankName() != null){
			beneficiary.setBankName(beneficiary1.getBankName());	
		}
		validModify = beneficiaryDao.updateDetails(uci, beneficiary);
		return validModify;
	}

	@Override
	public boolean deleteBeneficiaryAccountDetails(String uci, BigInteger accountNumber) throws IBSExceptions {
		return beneficiaryDao.deleteDetails(uci, accountNumber);
	}

	@Override
	public boolean saveBeneficiaryAccountDetails(String uci, Beneficiary beneficiary) throws IBSExceptions {
		beneficiaryDao.copyDetails(uci, beneficiary);
		return true;
	}

}
