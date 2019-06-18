package com.revature.banking;

public interface customerAction {
	void deposit(double amount, int accountNum);
	void withdraw(double amount, int accountNum);
	void transfer(double amount, int accountNum1, int accountNum2);
	void close(int accountNum);
	void open(String acntType, String firstName, String lastName, String userName, String password, double balance);
	void display(int accountNum);
	Customer login(int accountNum, String username, String password);
}
