package com.revature.banking;

public class Customer extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2788622008317034552L;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Integer balance) {
		super(balance);
		// TODO Auto-generated constructor stub
	}

	public Customer(String username, String password, int acntNum) {
		super(username, password, acntNum, balance);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public double getBalance() {
		// TODO Auto-generated method stub
		return super.getBalance();
	}

	@Override
	public void setBalance(double balance) {
		// TODO Auto-generated method stub
		super.setBalance(balance);
	}

	@Override
	public void login(String username, String password) {
		System.out.println("Customer");
		System.out.println("Username: " + username + "\nPassword: "+ password);
	}

	@Override
	public void account(int accountNumber) {
		System.out.println("Account: " + accountNumber);
		System.out.println("Balance: $" + getBalance() + "\n");
	}

	@Override
	public void deposit(double amount, int accountNum) {
		// TODO Auto-generated method stub
		super.deposit(amount, accountNum);
	}

	@Override
	public void withdraw(double amount, int accountNum) {
		// TODO Auto-generated method stub
		super.withdraw(amount, accountNum);
	}

	@Override
	public void transfer(double amount, int accountNum1, int accountNum2) {
		// TODO Auto-generated method stub
		super.transfer(amount, accountNum1, accountNum2);
	}

	@Override
	public void close(int accountNum) {
		// TODO Auto-generated method stub
		super.close(accountNum);
	}

	@Override
	public void open(String username, String password) {
		// TODO Auto-generated method stub
		super.open(username, password);
	}
	@Override
	public void initialDeposit(double amount) {
		// TODO Auto-generated method stub
		setBalance(amount);
	}
}