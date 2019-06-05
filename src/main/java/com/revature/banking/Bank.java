package com.revature.banking;

public class Bank {
	public static void main(String[] args) {
		User[] accounts = {new Customer(), new SuperUser()};
		for(User t : accounts)
		{
			t.login("John Smith", "password");
		}
	}
}
