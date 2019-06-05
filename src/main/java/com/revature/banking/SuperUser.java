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
		super(username, password, acntNum);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void login(String username, String password) {
		System.out.println("Administrator");
		System.out.println("Username: " + username + "\nPassword: "+ password);
	}

	@Override
	public void account(int accountNumber) {
		System.out.println("Account: " + accountNumber + "\n");
	}
	
}
