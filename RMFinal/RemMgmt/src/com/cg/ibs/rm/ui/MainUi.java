package com.cg.ibs.rm.ui;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.cg.ibs.rm.bean.AutoPayment;
import com.cg.ibs.rm.bean.Beneficiary;
import com.cg.ibs.rm.bean.CreditCard;
import com.cg.ibs.rm.bean.ServiceProvider;
import com.cg.ibs.rm.exception.IBSExceptions;
import com.cg.ibs.rm.service.AutoPaymentService;
import com.cg.ibs.rm.service.AutoPaymentServiceImpl;
import com.cg.ibs.rm.service.BankRepresentativeService;
import com.cg.ibs.rm.service.BankRepresentativeServiceImpl;
import com.cg.ibs.rm.service.BeneficiaryAccountService;
import com.cg.ibs.rm.service.BeneficiaryAccountServiceImpl;
import com.cg.ibs.rm.service.CreditCardService;
import com.cg.ibs.rm.service.CreditCardServiceImpl;

public class MainUi {
	private static Scanner scanner;
	private static Iterator<CreditCard> itCredit;
	private static Iterator<Beneficiary> itBeneficiary;

	private Set<ServiceProvider> serviceProviders;
	private CreditCardService cardService = new CreditCardServiceImpl();
	private BeneficiaryAccountService beneficiaryAccountService = new BeneficiaryAccountServiceImpl();
	private BankRepresentativeService bankRepresentativeService = new BankRepresentativeServiceImpl();
	private AutoPaymentService autopaymentservobj = new AutoPaymentServiceImpl();
	private Set<String> customerRequests;
	private String uci;

	private void Start() {
		MainMenu choice = null;
		while (MainMenu.QUIT != choice) {
			System.out.println("------------------------");
			System.out.println("Choose your identity from MENU:");
			System.out.println("------------------------");
			for (MainMenu menu : MainMenu.values()) {
				System.out.println((menu.ordinal() + 1) + "\t" + menu);
			}
			System.out.println("Your choice :");// choosing of identity whether
												// user or bank representative
			int ordinal = scanner.nextInt() - 1;

			if (0 <= (ordinal) && MainMenu.values().length > ordinal) {
				choice = MainMenu.values()[ordinal];
				switch (choice) {
				case CUSTOMER:
					login();
					customerAction();
					break;
				case BANKREPRESENTATIVE:
					bankRepresentativeAction();
					break;
				case QUIT:
					System.out.println("Thankyou... Visit again!");
					break;
				}
			} else {
				System.out.println("Please enter a valid option.");
				choice = null;
			}
		}
	}

	private void login() {// to enter login details
		int test;
		do {
			test = 0;
			System.out.println("Customer id:");
			uci = scanner.next();
			if (bankRepresentativeService.showRequests().contains(uci)) {
				System.out.println("Password");
				scanner.next();
				System.out.println("Logged in successfully!!");
			} else {
				System.out.println("Customer ID doesn't exist");
				test = 1;
			}
		} while (1 == test);

	}

	private void customerAction() {// facilities provided to the IBS customer
		CustomerUi choice = null;
		System.out.println("------------------------");
		System.out.println("Choose the desired action");
		System.out.println("------------------------");
		for (CustomerUi menu : CustomerUi.values()) {
			System.out.println(menu.ordinal() + 1 + "\t" + menu);// showing
																	// options
																	// to the
																	// customer
		}
		System.out.println("Choices:");
		int ordinal = scanner.nextInt() - 1;

		if (0 <= ordinal && CustomerUi.values().length > ordinal) {
			choice = CustomerUi.values()[ordinal];
			switch (choice) {
			case CREDITCARD:
				System.out.println("\nThe credit cards already existing for the customer:");
				int count = 1;
				for (CreditCard card : cardService.showCardDetails(uci)) {
					System.out.println(count + ") " + "Name on the card : " + card.getnameOnCreditCard()
							+ ("\n   Card number : " + card.getcreditCardNumber()) + "\n   Expiry date : "
							+ card.getcreditDateOfExpiry());
					count++;
				}
				addOrDeleteCreditCard();
				customerAction();
				break;
			case BENEFICIARY:

				addOrModifyBeneficiary();
				customerAction();
				break;
			case AUTOPAYMENT:
				System.out.println("\nThe added autopayment services for the customer.");
				int count2 = 1;
				for (AutoPayment autoPayment : autopaymentservobj.showAutopaymentDetails(uci)) {
					System.out.println(count2 + ") " + "Customer ID : " + uci + "\n   Service Provider ID : "
							+ autoPayment.getServiceProviderId() + "\n   Amount set to be deducted : "
							+ autoPayment.getAmount() + "\n   Date of start : " + autoPayment.getDateOfStart());
					count2++;
				}
				addOrRemoveAutopayments();
				customerAction();
				break;
			case EXIT:
				System.out.println("BACK ON HOME PAGE!!");
				Start();
				break;
			}
		} else {
			System.out.println("Please enter a valid option.");
			customerAction();
		}
	}

	private void addOrDeleteCreditCard() {
		CreditCard card = new CreditCard();
		int creditCardOption;
		do {
			System.out.println("Enter 1 to add a credit card. \nEnter 2 to delete a credit card \nEnter 3 to exit.");
			creditCardOption = scanner.nextInt();// enter the credit card

			switch (creditCardOption) {
			case 1:
				System.out.println("Please Enter your CreditCard number (16 digits)");
				BigInteger cardNumber = scanner.nextBigInteger();
				boolean valid = cardService.validateCardNumber(cardNumber);
				if (valid) {
					card.setcreditCardNumber(cardNumber);
				} else {
					while (!valid) {
						System.out.println("Enter the correct details again");
						cardNumber = scanner.nextBigInteger();
						valid = cardService.validateCardNumber(cardNumber);
					}
					card.setcreditCardNumber(cardNumber);
				}

				System.out.println("Please enter the Name on your CreditCard (Please ensure no spaces)");
				String nameOnCard = scanner.next();
				boolean valid2 = cardService.validateNameOnCard(nameOnCard);

				if (valid2) {

					card.setnameOnCreditCard(nameOnCard);
				} else {
					while (!valid2) {
						System.out.println("Enter the correct details again");
						nameOnCard = scanner.next();
						valid2 = cardService.validateNameOnCard(nameOnCard);
					}
					card.setnameOnCreditCard(nameOnCard);
				}
				System.out.println("Please enter the expiry date on your card number in (DD/MM/YYYY) format.");
				String expiryDate = scanner.next();
				while (expiryDate.length() != 10) {
					System.out.println("Enter a date that is not stupid.");
					expiryDate = scanner.next();
				}
				if (expiryDate.length() == 10) {
					boolean valid3;
					try {
						valid3 = cardService.validateDateOfExpiry(expiryDate);

						if (valid3) {
							card.setcreditDateOfExpiry(expiryDate);
						} else {
							while (!valid3) {
								System.out.println("Enter the correct details again.");
								expiryDate = scanner.next();
								valid3 = cardService.validateDateOfExpiry(expiryDate);
							}
							card.setcreditDateOfExpiry(expiryDate);
						}
						cardService.saveCardDetails(uci, card);
						System.out.println("\nDetails entered by you :" + "\nCard name : " + card.getnameOnCreditCard()
								+ "\nCard Number : " + card.getcreditCardNumber() + "\nExpiry date :"
								+ card.getcreditDateOfExpiry());
						System.out.println("\nCard gone for approval.. Good luck!!");
					} catch (IBSExceptions exception) {
						System.out.println(exception.getMessage());
					}
				}
				break;
			case 2:
				System.out.println("Enter the card number.");
				BigInteger creditCardNumber = scanner.nextBigInteger();
				boolean valid5 = cardService.validateCardNumber(creditCardNumber);
				if (valid5) {
					try {
						cardService.deleteCardDetails(uci, creditCardNumber);
						System.out.println("Card deleted!!");
					} catch (IBSExceptions exception) {
						System.out.println(exception.getMessage());
					}
				} else {
					while (!valid5) {
						System.out.println("Enter the correct details again");
						cardNumber = scanner.nextBigInteger();
						valid5 = cardService.validateCardNumber(cardNumber);
					}
				}
				break;
			case 3:
				customerAction();
				break;
			default:
				System.out.println("Please enter a valid choice");
				creditCardOption = 0;
			}
		} while (0 == creditCardOption);
	}

	private void addOrModifyBeneficiary() {
		// beneficiary related facilities provided in this section
		int count = 1;
		for (Beneficiary beneficiary : beneficiaryAccountService.showBeneficiaryAccount(uci)) {
			count++;
		}
		if (1 == count) {
			System.out.println("\nNo beneficiaries added yet!! Please go on and try our service.");
		} else {
			System.out.println("\nThe beneficairies already existing for the customer:");
		}
		int count1 = 1;
		for (Beneficiary beneficiary : beneficiaryAccountService.showBeneficiaryAccount(uci)) {
			System.out.println(count1 + ") " + "Name of the beneficiary : " + beneficiary.getAccountName()
					+ "\n   Beneficiary account number : " + beneficiary.getAccountNumber() + "\n   Bank name : "
					+ beneficiary.getBankName() + "\n   IFSC code : " + beneficiary.getIfscCode());
			count1++;
		}

		int beneficiaryOption;
		do {
			System.out.println(
					"\nEnter 1 to add a beneficiary. \nEnter 2 to modify a beneficiary. \nEnter 3 to delete a beneficiary. \nEnter 4 to exit.");
			beneficiaryOption = scanner.nextInt();// To choose the facility.
			switch (beneficiaryOption) {
			case 1:
				Type choice = null;
				System.out.println("------------------------");
				System.out.println("Choose the desired option");
				System.out.println("------------------------");
				for (Type menu : Type.values()) {
					System.out.println(menu.ordinal() + 1 + "\t" + menu);
				}
				System.out.println("Choices:");
				int ordinal = scanner.nextInt() - 1;

				if (0 <= ordinal && CustomerUi.values().length > ordinal) {
					choice = Type.values()[ordinal];
					switch (choice) {
					case SELFINSAME:
						addBeneficiary(choice);
						break;
					case SELFINOTHERS:
						addBeneficiary(choice);
					case OTHERSINOTHERS:
						addBeneficiary(choice);
						break;
					case OTHERSINSAME:
						addBeneficiary(choice);
						break;
					}
				} else {
					System.out.println("Please enter a valid option.");
					addOrModifyBeneficiary();
				}
				break;
			case 2:
				BigInteger accountNumberToModify;
				if (1 != count1) {
					int count5;
					do {
						System.out.println("\nPlease enter a valid account number");
						accountNumberToModify = scanner.nextBigInteger();
						count5 = 0;
						for (Beneficiary beneficiary : beneficiaryAccountService.showBeneficiaryAccount(uci)) {
							if (beneficiary.getAccountNumber().equals(accountNumberToModify)) {
								count5++;
							}
						}
					} while (count5 == 0);

					int choiceToModify;
					Beneficiary beneficiary = new Beneficiary();
					do {
						System.out.println(
								"\nEnter 1 to change the Account Holder Name. \nEnter 2 to change the IFSC code. \nEnter 3 to change the bank name."
										+ "\nEnter 4 to save changes. (Once saved changes, you can't change again till bank verification is done) \nEnter 5 to exit.");
						choiceToModify = scanner.nextInt();
						switch (choiceToModify) {
						case 1:
							System.out.println("Enter new account holder name");
							String nameInAccount = scanner.next();
							boolean validName = beneficiaryAccountService
									.validateBeneficiaryAccountNameOrBankName(nameInAccount);

							if (validName) {
								beneficiary.setAccountName(nameInAccount);
							} else {
								while (!validName) {
									System.out.println("Please enter the correct details again");
									nameInAccount = scanner.next();
									validName = beneficiaryAccountService
											.validateBeneficiaryAccountNameOrBankName(nameInAccount);
								}
								beneficiary.setAccountName(nameInAccount);
							}
							choiceToModify = 0;
							break;

						case 2:
							System.out.println("Enter new IFSC code");
							String ifscNewValue = scanner.next();
							boolean validIfsc = beneficiaryAccountService.validateBeneficiaryIfscCode(ifscNewValue);

							if (validIfsc) {
								beneficiary.setIfscCode(ifscNewValue);
							} else {
								while (!validIfsc) {
									System.out.println("Enter the correct details again");
									ifscNewValue = scanner.next();
									validIfsc = beneficiaryAccountService.validateBeneficiaryIfscCode(ifscNewValue);
								}
								beneficiary.setIfscCode(ifscNewValue);
							}
							choiceToModify = 0;
							break;

						case 3:
							System.out.println("Enter the new bank name");
							String bankNameNewValue = scanner.next();
							boolean validbankName = beneficiaryAccountService
									.validateBeneficiaryAccountNameOrBankName(bankNameNewValue);

							if (validbankName) {
								beneficiary.setBankName(bankNameNewValue);
							} else {
								while (!validbankName) {
									System.out.println("Enter the correct details again");
									bankNameNewValue = scanner.next();
									validbankName = beneficiaryAccountService
											.validateBeneficiaryAccountNameOrBankName(bankNameNewValue);
								}
								beneficiary.setBankName(bankNameNewValue);
							}
							choiceToModify = 0;
							break;
						case 4:
							try {
								beneficiaryAccountService.modifyBeneficiaryAccountDetails(uci, accountNumberToModify,
										beneficiary);
								System.out.println("\nModified beneficiary details are gone for approval.");
							} catch (IBSExceptions exception) {
								System.out.println(exception.getMessage());
							}
							addOrModifyBeneficiary();
							break;

						case 5:
							addOrModifyBeneficiary();
							break;

						default:
							System.out.println("Wrong Input");
							choiceToModify = 0;
							break;
						}
					} while (0 == choiceToModify);
				} else {
					System.out.println("\nNo beneficiary accounts to modify.");
				}
				break;
			case 3:
				if (1 != count1) {
					System.out.println("Enter the account number to be deleted.");
					BigInteger deleteAccountNum = scanner.nextBigInteger();
					try {
						beneficiaryAccountService.deleteBeneficiaryAccountDetails(uci, deleteAccountNum);
						System.out.println("Account deleted Successfully");
					} catch (IBSExceptions exception) {
						System.out.println(exception.getMessage());
					}
				} else {
					System.out.println("\nYou need to add beneficiaries first...!");
				}
				addOrModifyBeneficiary();
				break;
			case 4:
				customerAction();
				break;
			default:
				System.out.println("Please enter a valid choice");
				beneficiaryOption = 0;
			}
		} while (0 == beneficiaryOption);
	}

	private void addBeneficiary(Type type) {
		Beneficiary beneficiary = new Beneficiary();
		System.out.println("Please enter your Account number(12 digits)");
		BigInteger accountNumber = scanner.nextBigInteger();
		boolean valid = beneficiaryAccountService.validateBeneficiaryAccountNumber(accountNumber);
		if (valid) {
			beneficiary.setAccountNumber(accountNumber);
		} else {
			while (!valid) {
				System.out.println("Enter the correct details again");
				accountNumber = scanner.nextBigInteger();
				valid = beneficiaryAccountService.validateBeneficiaryAccountNumber(accountNumber);
			}
			beneficiary.setAccountNumber(accountNumber);
		}

		System.out.println("Please enter the Account Holder Name (Case Sensitive)");
		String nameInAccount = scanner.next();
		boolean valid2 = beneficiaryAccountService.validateBeneficiaryAccountNameOrBankName(nameInAccount);

		if (valid2) {

			beneficiary.setAccountName(nameInAccount);
		} else {
			while (!valid2) {
				System.out.println("Enter the correct details again");
				nameInAccount = scanner.next();
				valid2 = beneficiaryAccountService.validateBeneficiaryAccountNameOrBankName(nameInAccount);
			}
			beneficiary.setAccountName(nameInAccount);
		}

		System.out.println("Please enter the IFSC code(11 characters)");
		String ifsc = scanner.next();
		boolean valid3 = beneficiaryAccountService.validateBeneficiaryIfscCode(ifsc);
		if (valid3) {

			beneficiary.setIfscCode(ifsc);
		} else {
			while (!valid3) {
				System.out.println("Enter the correct details again");
				ifsc = scanner.next();
				valid3 = beneficiaryAccountService.validateBeneficiaryIfscCode(ifsc);
			}
			beneficiary.setIfscCode(ifsc);
		}

		System.out.println("Enter the bank name (case sensitive)");
		String bankName = scanner.next();
		boolean valid4 = beneficiaryAccountService.validateBeneficiaryAccountNameOrBankName(bankName);

		if (valid4) {

			beneficiary.setBankName(bankName);
		} else {
			while (!valid4) {
				System.out.println("Enter the correct details again");
				bankName = scanner.next();
				valid4 = beneficiaryAccountService.validateBeneficiaryAccountNameOrBankName(bankName);
			}
			beneficiary.setBankName(bankName);
		}
		beneficiary.setType(type);
		try {
			beneficiaryAccountService.saveBeneficiaryAccountDetails(uci, beneficiary);
			System.out.println("\nThe details entered by you are : " + "\nName of the beneficiary : "
					+ beneficiary.getAccountName() + "\nBeneficiary account number : " + beneficiary.getAccountNumber()
					+ "\nBank name : " + beneficiary.getBankName() + "\nIFSC code : " + beneficiary.getIfscCode());
			System.out.println("Beneficiary gone for approval... Good luck!!");
		} catch (IBSExceptions exception) {
			System.out.println(exception.getMessage());
		}
	}

	private void addOrRemoveAutopayments() {
		AutoPaymentUi choice = null;
		System.out.println("------------------------");
		System.out.println("Choose a valid option");
		System.out.println("------------------------");
		for (AutoPaymentUi menu : AutoPaymentUi.values()) {
			System.out.println(menu.ordinal() + 1 + "\t" + menu);
		}
		System.out.println("Choices:");
		int ordinal = scanner.nextInt() - 1;

		if (0 <= ordinal && AutoPaymentUi.values().length > ordinal) {
			choice = AutoPaymentUi.values()[ordinal];
			switch (choice) {
			case ADDAUTOPAYMENTS:

				addAutoPayment(); // this method is for adding auto payment
									// details
				addOrRemoveAutopayments();// this method is for showing auto
											// payment menu again
				break;
			case REMOVEAUTOPAYMENTS:
				removeAutoPayment();
				addOrRemoveAutopayments();
				break;
			case EXIT:
				System.out.println("BACK ON HOME PAGE!!");
				break;
			default:
				System.out.println("Enter a valid choice");
				addOrRemoveAutopayments();
			}
		} else {
			System.out.println("Please enter a valid option.");
			addOrRemoveAutopayments();
		}
	}

	private void addAutoPayment() {
		BigInteger input;// for service provider id as given by use case 5
		AutoPayment autoPayment = new AutoPayment();
		serviceProviders = autopaymentservobj.showIBSServiceProviders();
		boolean check;
		System.out.println("\nThe IBS service providers are : ");
		for (ServiceProvider serviceProvider : serviceProviders) {
			System.out.println("\nName : " + serviceProvider.getNameOfCompany() + "\nService Provider ID : "
					+ serviceProvider.getSpi());
		}
		int count5;
		do {
			System.out.println("\nEnter a valid service provider id to be registerd.");
			input = scanner.nextBigInteger();

			count5 = 0;
			for (ServiceProvider serviceProvider : serviceProviders) {
				if (serviceProvider.getSpi().equals(input)) {
					count5++;
				}
			}
		} while (count5 == 0);

		autoPayment.setServiceProviderId(input);
		System.out.println("Enter the amount to be deducted");
		BigDecimal amount = scanner.nextBigDecimal();
		autoPayment.setAmount(amount);

		int choice3;
		do {
			choice3 = 0;

			System.out.println("Enter your start date (in format dd/MM/yyyy)");
			String mydate = scanner.next();
			while (mydate.length() != 10) {
				System.out.println("Enter a Date that is not stupid ");
				mydate = scanner.next();
			}
			if (mydate.length() == 10) {
				autoPayment.setDateOfStart(mydate);
				try {
					check = autopaymentservobj.autoDeduction(uci, autoPayment);
					if (check) {
						System.out.println("AutoPayment of service provider: " + input + " added and Rs. " + amount
								+ " will be deducted per month from the date of start");
					} else {
						System.out.println("Autopayment service could not be added");
					}
				} catch (IBSExceptions exception) {
					choice3 = 1;
					System.out.println(exception.getMessage());
				}
			}
		} while (choice3 == 1);
	}

	private void removeAutoPayment() {// removal of autopayment

		System.out.println("\nThe added autopayment services for the customer.");
		int count3 = 1;
		for (AutoPayment autoPayment : autopaymentservobj.showAutopaymentDetails(uci)) {
			System.out.println(count3 + ") " + "Customer ID : " + uci + "\n   Service Provider ID : "
					+ autoPayment.getServiceProviderId() + "\n   Amount set to be deducted : " + autoPayment.getAmount()
					+ "\n   Date of start : " + autoPayment.getDateOfStart());
			count3++;
		}
		try {
			BigInteger input3;
			int count6;
			do {
				System.out.println("Enter a valid sevice provider id to be removed");
				input3 = scanner.nextBigInteger();
				count6 = 0;
				for (ServiceProvider serviceProvider : serviceProviders) {
					if (serviceProvider.getSpi().equals(input3)) {
						count6++;
					}
				}
			} while (count6 == 0);

			autopaymentservobj.updateRequirements(uci, input3);
			System.out.println("Autopayment service removed successfully");
		} catch (IBSExceptions exception) {
			System.out.println(exception.getMessage());
		}
	}

	private void bankRepresentativeAction() {// user interface for bank
												// representative
		BankRepresentativeUi choice = null;
		System.out.println("------------------------");
		System.out.println("Choose a valid option");
		System.out.println("------------------------");
		for (BankRepresentativeUi menu : BankRepresentativeUi.values()) {
			System.out.println(menu.ordinal() + 1 + "\t" + menu);
		}
		System.out.println("Choices:");
		int ordinal = scanner.nextInt() - 1;

		if (0 <= ordinal && BankRepresentativeUi.values().length > ordinal) {
			choice = BankRepresentativeUi.values()[ordinal];
			switch (choice) {
			case VIEWREQUESTS:

				showRequests();

				break;
			case EXIT:
				System.out.println("BACK ON HOME PAGE!!");
				break;
			}
		} else {
			System.out.println("Please enter a valid option.");
			bankRepresentativeAction();
		}
	}

	private void showRequests() {

		customerRequests = bankRepresentativeService.showRequests();
		System.out.println("The following customers have new approval requests");
		for (String customerRequest : customerRequests) {
			int count = 1;
			System.out.println(count + " : " + customerRequest);
			count++;
		}
		String uci;
		System.out.println("Please enter the customer id to view individual requests \nEnter 1 to exit");
		uci = scanner.next();
		if (uci.equals("1")) {
			bankRepresentativeAction();
		} else if (customerRequests.contains(uci)) {

			int choice7 = 0;
			do {
				System.out.println("Id entered by you is : " + uci);
				int choice1;
				System.out.println(
						"\nEnter 1 to view Creditcard requests. \nEnter 2 to view Beneficiary Requests. \nEnter 3 to exit.");
				choice1 = scanner.nextInt();
				switch (choice1) {
				case 1:
					try {
						itCredit = bankRepresentativeService.showUnapprovedCreditCards(uci).iterator();

						if (!(itCredit.hasNext())) {
							System.out.println("No more credit card requests");
							break;
						}
						while (itCredit.hasNext()) {
							CreditCard creditCard = itCredit.next();
							System.out.println("Credit card number : " + creditCard.getcreditCardNumber()
									+ "\nCredit card expiry : " + creditCard.getcreditDateOfExpiry() +

									"\nName on the credit card : " + creditCard.getnameOnCreditCard());
							int choice2;
							do {
								System.out.println(
										"\nPress 1 or 2 according to the decision and proceed to the next card."
												+ "\nEnter 1 to approve. \nEnter 2 to disapprove. \nEnter 3 to exit this section.");
								choice2 = scanner.nextInt();
								switch (choice2) {
								case 1:
									boolean valid7 = false;
									valid7 = bankRepresentativeService.saveCreditCardDetails(uci, creditCard);
									if (valid7) {
										System.out.println("Card approved by the bank representative.");
									}

									itCredit.remove();
									break;
								case 2:
									itCredit.remove();
									System.out.println("Card disapproved by the bank representative.");
									break;

								case 3:
									choice7 = 1;
									break;

								default:
									System.out.println("Enter a valid choice of decision of credit card");
									choice2 = 0;
								}
							} while (0 == choice2);
						}
					} catch (IBSExceptions exception) {
						exception.getMessage();
					}
					break;
				case 2:
					try {
						// showing beneficiary requests to bank representative
						itBeneficiary = bankRepresentativeService.showUnapprovedBeneficiaries(uci).iterator();

						if (!(itBeneficiary.hasNext())) {
							System.out.println("No more beneficiary requests");
							break;
						}
						while (itBeneficiary.hasNext()) {
							Beneficiary beneficiary = itBeneficiary.next();
							System.out.println("Beneficiary name : " + beneficiary.getAccountName()
									+ "\nBeneficiary Account number : " + beneficiary.getAccountNumber()
									+ "\nBank name : " + beneficiary.getBankName() + "\nIFSC code : "
									+ beneficiary.getIfscCode());
							int choice2;
							do {
								System.out.println(
										"\nPress 1 or 2 according to the decision and proceed to the next beneficiary."
												+ "\nEnter 1 to approve. \nEnter 2 to disapprove. \nEnter 3 to exit this section.");
								choice2 = scanner.nextInt();
								switch (choice2) {
								case 1:
									bankRepresentativeService.saveBeneficiaryDetails(uci, beneficiary);

									System.out.println("Beneficiary approved by the bank representative.");
									itBeneficiary.remove();
									break;
								case 2:
									itBeneficiary.remove();
									System.out.println("Beneficiary disapproved  by the bank representative.");
									break;
								case 3:
									choice7 = 1;
									break;

								default:
									System.out.println("Enter a valid choice of decision of beneficiary");
									choice2 = 0;
								}
							} while (0 == choice2);
						}
					} catch (IBSExceptions exception) {
						System.out.println(exception.getMessage());
					}
					break;
				case 3:
					showRequests();
					break;
				default:
					System.out.println("Enter a valid choice of action");
				}
			} while (choice7 == 1);

		} else {
			System.out.println("\nInvalid customer ID");
			showRequests();
		}

	}

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		MainUi mainUii = new MainUi();
		mainUii.Start();
		scanner.close();
		System.out.println();
	}
}
