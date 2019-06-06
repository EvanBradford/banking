package com.revature.banking;

public class SuperUser extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5319764797495658012L;

	public SuperUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SuperUser(String username, String password) {
		super(username, password, acntNum, balance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void login(String username, String password) {
		System.out.println("Administrator");
		System.out.println("Username: " + username + "\nPassword: "+ password);
	}

	@Override
	public void account(int accountNumber) {
		System.out.println("Account: " + accountNumber);
		System.out.println("Balance: $" + getBalance() + "\n");
	}

	@Override
	public void viewAllCust() {
		// TODO Auto-generated method stub
		super.viewAllCust();
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
	
}
