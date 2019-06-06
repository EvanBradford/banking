package com.revature.banking;

public interface Login {
	void login(String username, String password);
	void account(int accountNumber);
	void initialDeposit(double amount);
}
