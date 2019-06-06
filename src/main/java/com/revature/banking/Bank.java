package com.revature.banking;

public class Bank {
	public static void main(String[] args) {
		User[] accounts = {new SuperUser(), new Customer(), new Customer(), new SuperUser()};
		int number = 123456;
		double deposit1 = 5000.00;
		double deposit2 = 1000.00;
		for(User t : accounts)
		{
			t.login("John Smith", "password");
			t.account(number);
			t.initialDeposit(deposit1);
			t.deposit(deposit2, number);
			t.withdraw(deposit1, number);
			number++;
			deposit1 += 1500.51;
			deposit2 += 500.01;
		}
	}
}
