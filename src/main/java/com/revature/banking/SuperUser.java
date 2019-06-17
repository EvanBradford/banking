package com.revature.banking;

import java.text.DecimalFormat;
import java.util.List;

import dao.BankService;

public class SuperUser extends Bankaccounts{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5319764797495658012L;

	public SuperUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SuperUser(int acntNum, String acntType, String firstname, String lastname, String username, String password, double balance)
	{
		super(acntNum, acntType, firstname, lastname, username, password, balance);
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		super.viewAllTrans();
	}
	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		super.delete();
	}
	@Override
	public void initialDeposit(double amount) {
		// TODO Auto-generated method stub
		setBalance(amount);
	}

	@Override
	public void display(int accountNum) {
		// TODO Auto-generated method stub	
	}
	@Override
	public Customer login(int accountNum, String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}