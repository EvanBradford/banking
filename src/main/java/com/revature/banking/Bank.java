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
		double amount;
		String response;
		String response2;
		while(loggingIn)
		{
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
				System.out.println("Are you an administrator? Yes/No");
				response2 = jin.nextLine();
				response2 = response2.toUpperCase();
				if(response2.equals("YES"))
				{
					admin = admin.login(accountNum, username, password);
					loggingIn = false;
				}
				else
				{
					account = account.login(accountNum, username, password);
					loggingIn = false;
				}
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
				account = account.login(accountNum, username, password);
				loggingIn = false;
			}
		}
		while(banking)
		{
			if(account.getAcntType() == "Customer")
			{
				System.out.println("1 - Deposit\n2 - Withdraw\n3 - Transfer\n4 - Open Account\n5 - Close Account\n6 - Display Account\n7 - Quit");
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
						System.out.println("How much are you transferring?");
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
			else if(account.getAcntType() == "Administrator")
			{
				System.out.println("1 - Create Customer\n2 - View All Accounts\n3 - View All Transactions\n4 - Quit");
				choice = jin.nextInt();
				switch(choice)
				{
					case 1:
						break;
					case 2:
						admin.viewAllCust();
						break;
					case 3:
						break;
					case 4:
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