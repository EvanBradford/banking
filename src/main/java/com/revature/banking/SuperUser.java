package com.revature.banking;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.BankService;

public class SuperUser extends Bankaccounts{
	
	/**
	 * 
	 */
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static final long serialVersionUID = -5319764797495658012L;

	public SuperUser() {
		super();
	}
	public SuperUser(int acntNum, String acntType, String firstname, String lastname, String username, String password, double balance)
	{
		super(acntNum, acntType, firstname, lastname, username, password, balance);
	}


	@Override
	public SuperUser loginu(int acntNum, String username, String password) {
		SuperUser tmp = new SuperUser();
		try {
			tmp = BankService.loginu(acntNum, username, password);
		} catch (Exception e) {
			System.out.println("Oops.. (^_^)");
			return null;
		}
		return tmp;
	}

	@Override
	public void account(int accountNumber) {
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		System.out.println("Account: " + accountNumber);
		System.out.println("Balance: $" + df.format(getBalance()) + "\n");
	}
	@Override
	public void viewAllCust() {
		List<Bankaccounts> list;
		try {		
			list = BankService.getAllAccounts();
		} catch (Exception e) {
			System.out.println("Opps.. (^_^)");
			return;
		}
		System.out.println(list);
	}
	@Override
	public void viewAllTrans() {
		Date date = new Date();
        System.out.println(sdf.format(date));
	}
	@Override
	public void create(String acntType, String firstName, String lastName, String userName, String password, double balance) {
		try {		
			BankService.insert(acntType, firstName, lastName, userName, password, balance);
		} catch (Exception e) {
			System.out.println("Oops.. (^_^)");
			return;
		}
		System.out.println("Account Created");
		return;
	}
	@Override
	public void delete(int accountNum) {
		try {		
			BankService.delete(accountNum);
		} catch (Exception e) {
			System.out.println("Oops.. (^_^)");
			return;
		}
		System.out.println("Account Deleted");
		return;
	}
	@Override
	public String toString() {
		return "\nAdmin:\nacntNum = " + acntNum + "\nacntType = " + acntType + "\nfirstname = " + firstname
				+ "\nlastname = " + lastname + "\nusername = " + username + "\npassword = " + password + "\nbalance = "
				+ balance + "\n";
	}
}