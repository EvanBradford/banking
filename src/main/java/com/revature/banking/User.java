package com.revature.banking;

import java.io.Serializable;

public abstract class User implements Serializable, Login, customerAction, adminAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4981386263987485356L;
	protected String username;
	protected String password;
	protected static int acntNum;
	protected static double balance;
	
	public User()
	{
		
	}

	public User(String username, String password, int acntNum, double balance) {
		super();
		this.username = username;
		this.password = password;
		User.acntNum = acntNum;
		User.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAcntNum() {
		return acntNum;
	}

	public void setAcntNum(int acntNum) {
		User.acntNum = acntNum;
	}

	public User(double balance) {
		super();
		User.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		User.balance = balance;
	}

	@Override
	public void login(String username, String password) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void account(int accountNumber) {
		// TODO Auto-generated method stub	
	}
	@Override
	public void initialDeposit(double amount) {
		// TODO Auto-generated method stub	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public void deposit(double amount, int accountNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(double amount, int accountNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transfer(double amount, int accountNum1, int accountNum2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close(int accountNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewAllCust() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewAllTrans() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
}