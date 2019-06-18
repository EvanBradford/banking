package com.revature.banking;

import java.text.DecimalFormat;
import dao.BankService;

public class Customer extends Bankaccounts{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2788622008317034552L;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	//User(int acntNum, String acntType, String firstname, String lastname, String username, String password, double balance)
	public Customer(int acntNum, String acntType, String firstname, String lastname, String username, String password, double balance)
	{
		super(acntNum, acntType, firstname, lastname, username, password, balance);
		// TODO Auto-generated constructor stub
	}
	
	

	public double getBalance() {
		// TODO Auto-generated method stub
		return super.getBalance();
	}

	public void setBalance(double balance) {
		// TODO Auto-generated method stub
		super.setBalance(balance);
	}

	@Override
	public Customer login(int accountNum, String username, String password) {
		Customer tmp = new Customer();
		try {		
			tmp = BankService.login(accountNum, username, password);
		} catch (Exception e) {
			System.out.println("Oops.. (^_^)");
			return null;
		}
		return tmp;
	}

	@Override
	public void account(int accountNumber) {
	}

	@Override
	public void deposit(double amount, int accountNum) {
		double tmp = getBalance();
		tmp += amount;
		tmp = Math.round(tmp * 100.0) / 100.0;
		try {		
			BankService.updateAccount(acntNum, tmp);
		} catch (Exception e) {
			System.out.println("Oops.. (^_^)");
			return;
		}
		setBalance(tmp);
	}

	@Override
	public void withdraw(double amount, int accountNum) {
		double tmp = getBalance();
		if(tmp < amount)
		{
			System.out.println("Account overdrawn Aborting operation");
			return;
		}
		tmp -= amount;
		tmp = Math.round(tmp * 100.0) / 100.0;
		try {		
			BankService.updateAccount(acntNum, tmp);
		} catch (Exception e) {
			System.out.println("Oops.. (^_^)");
			return;
		}
		setBalance(tmp);
	}

	@Override
	public void transfer(double amount, int accountNum1, int accountNum2) {
		double tmp = getBalance();
		if(tmp < amount)
		{
			System.out.println("Account overdrawn Aborting operation");
			return;
		}
		tmp -= amount;
		tmp = Math.round(tmp * 100.0) / 100.0;
		try {		
			BankService.transferFunds(accountNum1,accountNum2, amount, tmp);
		} catch (Exception e) {
			System.out.println("Oops.. (^_^)");
			return;
		}
		setBalance(tmp);
	}

	@Override
	public void close(int accountNum) {
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
	public void open(String acntType, String firstName, String lastName, String userName, String password, double balance) {
		// TODO Auto-generated method stub
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
	public void initialDeposit(double amount) {
		// TODO Auto-generated method stub
		setBalance(amount);
	}

	@Override
	public void display(int accountNum) {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		System.out.println("Customer");
		System.out.println("Username: " + getUsername() + "\nPassword: "+ getPassword());
		System.out.println("Account: " + accountNum);
		System.out.println("Balance: $" + df.format(getBalance()) + "\n");
	}
	@Override
	public SuperUser loginu(int acntNum, String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public String toString() {
		return "\nCustomer:\nacntNum = " + acntNum + "\nacntType = " + acntType + "\nfirstname = " + firstname
				+ "\nlastname = " + lastname + "\nusername = " + username + "\npassword = " + password + "\nbalance = "
				+ balance + "\n";
	}
}