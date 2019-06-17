package com.revature.banking;

import java.io.Serializable;

public abstract class Bankaccounts implements Serializable, customerAction, adminAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4981386263987485356L;
	protected int acntNum;
	protected String acntType;
	protected String firstname;
	protected String lastname;
	protected String username;
	protected String password;
	protected double balance;
	
	public Bankaccounts()
	{
		
	}
	
	public Bankaccounts(int acntNum, String acntType, String firstname, String lastname, String username, String password, double balance) {
		super();
		this.acntNum = acntNum;
		this.acntType = acntType;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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
		this.acntNum = acntNum;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getAcntType() {
		return acntType;
	}

	public void setAcntType(String acntType) {
		this.acntType = acntType;
	}

	public Customer login(String username, String password) {
		return null;
	}
	public SuperUser loginu(String username, String password) {
		return null;
	}
	public void account(int accountNumber) {
	}
	public void initialDeposit(double amount) {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
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
		Bankaccounts other = (Bankaccounts) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
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
	

	public void viewAllCust() {
		// TODO Auto-generated method stub
		
	}

	public void viewAllTrans() {
		// TODO Auto-generated method stub
		
	}

	public void create() {
		// TODO Auto-generated method stub
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		
	}

	public void deposit(double amount, int accountNum) {
		// TODO Auto-generated method stub
		
	}

	public void withdraw(double amount, int accountNum) {
		// TODO Auto-generated method stub
		
	}

	public void transfer(double amount, int accountNum1, int accountNum2) {
		// TODO Auto-generated method stub
		
	}

	public void close(int accountNum) {
		// TODO Auto-generated method stub
		
	}

	public void open() {
		// TODO Auto-generated method stub
		
	}

	public void display(int accountNum) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		return "Bankaccounts [acntNum=" + acntNum + ", acntType=" + acntType + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", username=" + username + ", password=" + password + ", balance="
				+ balance + "]";
	}
}