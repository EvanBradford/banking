package com.revature.banking;

import java.util.List;
import java.util.Scanner;
import dao.BankService;

public class Bank {
	public static Scanner jin = new Scanner(System.in);
	public static void main(String[] args) {
		Bankaccounts account = new Customer();
		boolean banking = true;
		int choice = 1;
		int accountNum;
		int accountNum2;
		String username;
		String password;
		double amount;
		String response;
		System.out.println("Welcome to the bank do you have an account? Yes/No");
		response = jin.nextLine();
		response = response.toUpperCase();
		if(response.equals("YES"))
		{
			System.out.println("What is your account number?");
			accountNum = jin.nextInt();
			jin.nextLine();
			System.out.println("What is your username?");
			username = jin.nextLine();
			System.out.println("And what is your password?");
			password = jin.nextLine();
			account.login(username, password);
		}
		else
		{
			account.open();
			System.out.println("What is your account number?");
			accountNum = jin.nextInt();
			jin.nextLine();
			System.out.println("What is your username?");
			username = jin.nextLine();
			System.out.println("And what is your password?");
			password = jin.nextLine();
			account.login(username, password);
		}
		while(banking)
		{
			System.out.println("1 - Deposit\n2 - Withdraw\n3 - Transfer\n4 - Open Account\n5 - Close Account\n6 - Display Account\n7 - Quit");
			choice = jin.nextInt();
			switch(choice)
			{
				case 1:
					System.out.println("How much are you depositing?");
					amount = jin.nextDouble();
					account.deposit(amount, accountNum);
					break;
				case 2:
					System.out.println("How much are you withdrawing?");
					amount = jin.nextDouble();
					account.withdraw(amount, accountNum);
					break;
				case 3:
					System.out.println("How much are you withdrawing?");
					amount = jin.nextDouble();
					System.out.println("What account number are you transferring to?");
					accountNum2 = jin.nextInt();
					account.transfer(amount, accountNum, accountNum2);
					break;
				case 4:
					account.open();
					break;
				case 5:
					account.close(accountNum);
					break;
				case 6:
					account.display(accountNum);
					List<Bankaccounts> list;
					try {			
						list = BankService.getAllAccounts();
					} catch (Exception e) {
						System.out.println("Opps.. (^_^)");
						return;
					}
					System.out.println(list);
					break;
				case 7:
					banking = false;
					break;
			}
			if(banking)
			{
				System.out.println("If you would like to do more please choose another option: ");
			}
		}
		System.out.println("Thank you for banking with us!");
	}
}