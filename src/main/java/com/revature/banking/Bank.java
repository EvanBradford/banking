package com.revature.banking;

public class Bank {
	public static void main(String[] args) {
		User[] accounts = {new SuperUser(), new Customer(), new Customer()};
		int number = 123456;
		double deposit = 5000.00;
		for(User t : accounts)
		{
			t.login("John Smith", "password");
			t.account(number);
			t.initialDeposit(deposit);
			number++;
			deposit += 1500.51;
		}
	}
}
