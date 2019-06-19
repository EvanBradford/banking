package com.revature.banking;

import java.util.Scanner;

public class Bank 
{
	public static Scanner jin = new Scanner(System.in);
	public static void main(String[] args)
	{
		Bankaccounts account = new Customer();
		Bankaccounts admin = new SuperUser();
		boolean banking = true;
		boolean loggingIn = true;
		int choice = 1;
		int accountNum = 0;
		int accountNum2;
		String username;
		String password;
		String acntType, firstName, lastName;
		double balance;
		double amount;
		String response;
		String response2;
		while(loggingIn)
		{
			System.out.println("Welcome to the bank do you have an account? Yes/No (No will take you to create a new account)");
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
				System.out.println("Are you an administrator? Yes/No");
				response2 = jin.nextLine();
				response2 = response2.toUpperCase();
				if(response2.equals("YES"))
				{
					admin = admin.loginu(accountNum, username, password);
					if(admin.getAcntType() != null)
						loggingIn = false;
					else
						System.out.println("Username and password don't match or you aren't an admin, either way login failed, try again");
				}
				else
				{
					account = account.login(accountNum, username, password);
					if(account.getAcntType() != null)
							loggingIn = false;
					else
						System.out.println("Username and password don't match or this account was an admin, either way login failed, try again");
				}
			}
			else if(response.equals("NO"))
			{
				System.out.println("What is your first name?");
				firstName = jin.nextLine();
				System.out.println("What is your last name?");
				lastName = jin.nextLine();
				System.out.println("What would you like your username to be?");
				username = jin.nextLine();
				System.out.println("and your password?");
				password = jin.nextLine();
				System.out.println("How much money are you depositing to open your account?");
				System.out.print("$");
				balance = jin.nextDouble();
				account.open("CUSTOMER", firstName, lastName, username, password, balance);
			}
		}
		while(banking)
		{
			if(account.getAcntType() != null)
			{
				System.out.println("1 - Deposit\n2 - Withdraw\n3 - Transfer\n4 - Open Account\n5 - Close Account\n6 - Display Account\n7 - Quit");
				choice = jin.nextInt();
				switch(choice)
				{
					case 1:
						System.out.println("How much money are you depositing?");
						System.out.print("$");
						amount = jin.nextDouble();
						account.deposit(amount, account.getAcntNum());
						break;
					case 2:
						System.out.println("How much money are you withdrawing?");
						System.out.print("$");
						amount = jin.nextDouble();
						account.withdraw(amount, account.getAcntNum());
						break;
					case 3:
						//optional
						System.out.println("How much money are you transferring?");
						amount = jin.nextDouble();
						System.out.println("What account number are you transferring to?");
						accountNum2 = jin.nextInt();
						account.transfer(amount, account.getAcntNum(), accountNum2);
						break;
					case 4:
						System.out.println("What is your first name?");
						firstName = jin.nextLine();
						System.out.println("What is your last name?");
						lastName = jin.nextLine();
						System.out.println("What would you like your username to be?");
						username = jin.nextLine();
						System.out.println("and your password?");
						password = jin.nextLine();
						System.out.println("How much money are you depositing to open your account?");
						System.out.print("$");
						balance = jin.nextDouble();
						account.open("CUSTOMER", firstName, lastName, username, password, balance);
						break;
					case 5:
						account.close(account.getAcntNum());
						System.out.println("Please leave our establishment now that you are no longer our customer.");
						choice = 7;
						break;
					case 6:
						account.display(account.getAcntNum());
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
			else if(admin.getAcntType() != null)
			{
				System.out.println("1 - Create Customer\n2 - Delete\n3 - View All Accounts\n4 - View All Transactions\n5 - Quit");
				choice = jin.nextInt();
				switch(choice)
				{
					case 1:
						System.out.println("What type of account are you creating? (CUSTOMER/ ADMIN)");
						acntType = jin.nextLine();
						System.out.println("What is their first name?");
						firstName = jin.nextLine();
						System.out.println("What is their last name?");
						lastName = jin.nextLine();
						System.out.println("What would you like their username to be?");
						username = jin.nextLine();
						System.out.println("and their password?");
						password = jin.nextLine();
						System.out.println("How much money are they depositing to open your account?");
						System.out.print("$");
						balance = jin.nextDouble();
						admin.create(acntType, firstName, lastName, username, password, balance);
						break;
					case 2:
						System.out.println("What is the account number you are trying to delete?");
						accountNum = jin.nextInt();
						admin.delete(accountNum);
						break;
					case 3:
						admin.viewAllCust();
						break;
					case 4:
						System.out.println("WIP");
						admin.viewAllTrans();
						break;
					case 5:
						banking = false;
						break;
				}
				if(banking)
				{
					System.out.println("If you would like to do more please choose another option: ");
				}
			}
		}
		System.out.println("Thank you for banking with us!");
	}
}