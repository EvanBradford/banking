package com.revature.banking;

public class Customer extends User{

	float balance;
	/**
	 * 
	 */
	private static final long serialVersionUID = 2788622008317034552L;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String username, String password) {
		super(username, password, acntNum);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void login(String username, String password) {
		System.out.println("Customer");
		System.out.println("Username: " + username + "\nPassword: "+ password);
	}

	@Override
	public void account(int accountNumber) {
		System.out.println("Account: " + accountNumber + "\n");
	}
	
}
