package com.revature.banking;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Bank 
{
	public static Scanner jin = new Scanner(System.in);
	@SuppressWarnings("null")
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
				try {
					accountNum = jin.nextInt();
				} catch(ClassCastException cce){
					System.out.println("Your input is invalid, please try again");
			    } catch(InputMismatchException ime){
			        System.out.println("Your input is invalid, please try again");
			    }
				jin = new Scanner(System.in);
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
				jin = new Scanner(System.in);
				System.out.println("1 - Deposit\n2 - Withdraw\n3 - Transfer\n4 - Open Account\n5 - Close Account\n6 - Display Account\n7 - Quit");
				choice = jin.nextInt();
				switch(choice)
				{
					case 1:
						jin = new Scanner(System.in);
						boolean validOption = false;
						while(!validOption)
						{
							try {
								System.out.println("How much money are you depositing?");
								System.out.print("$");
								amount = jin.nextDouble();
								account.deposit(amount, account.getAcntNum());
							} catch(ClassCastException cce){
						        System.out.println("Your input is invalid, please try again");
						        validOption = false;
						    } catch(InputMismatchException ime){
						        System.out.println("Your input is invalid, please try again");
						        validOption = false;
						    }
							validOption = true;
						}
						break;
					case 2:
						jin = new Scanner(System.in);
						validOption = false;
						while(!validOption)
						{
							try {
								System.out.println("How much money are you withdrawing?");
								System.out.print("$");
								amount = jin.nextDouble();
								account.withdraw(amount, account.getAcntNum());
							} catch(ClassCastException cce){
						        System.out.println("Your input is invalid, please try again");
						        validOption = false;
						    } catch(InputMismatchException ime){
						        System.out.println("Your input is invalid, please try again");
						        validOption = false;
						    }
							validOption = true;
						}
						break;
					case 3:
						//optional
						jin = new Scanner(System.in);
						validOption = false;
						while(!validOption)
						{
							System.out.println("How much money are you transferring?");
							System.out.print("$");
							try {
								amount = jin.nextDouble();
								System.out.println("What account number are you transferring to?");
								accountNum2 = jin.nextInt();
								account.transfer(amount, account.getAcntNum(), accountNum2);
							} catch(ClassCastException cce){
						        System.out.println("Your input is invalid, please try again");
						        validOption = false;
						    } catch(InputMismatchException ime){
						        System.out.println("Your input is invalid, please try again");
						        validOption = false;
						    }
							validOption = true;
						}
						break;
					case 4:
						jin = new Scanner(System.in);
						System.out.println("What is your first name?");
						firstName = jin.nextLine();
						System.out.println("What is your last name?");
						lastName = jin.nextLine();
						System.out.println("What would you like your username to be?");
						username = jin.nextLine();
						System.out.println("and your password?");
						password = jin.nextLine();
						validOption = false;
						while(!validOption)
						{
							System.out.println("How much money are you depositing to open your account?");
							System.out.print("$");
							try {
								balance = jin.nextDouble();
								account.open("CUSTOMER", firstName, lastName, username, password, balance);
							} catch(ClassCastException cce){
						        System.out.println("Your input is invalid, please try again");
						        validOption = false;
						    } catch(InputMismatchException ime){
						        System.out.println("Your input is invalid, please try again");
						        validOption = false;
						    }
							validOption = true;
						}
						break;
					case 5:
						jin = new Scanner(System.in);
						account.close(account.getAcntNum());
						System.out.println("Please leave our establishment now that you are no longer our customer.");
						choice = 7;
						break;
					case 6:
						jin = new Scanner(System.in);
						account.display(account.getAcntNum());
						break;
					case 7:
						jin = new Scanner(System.in);
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
				jin = new Scanner(System.in);
				System.out.println("1 - Create Customer\n2 - Delete\n3 - View All Accounts\n4 - View All Transactions\n5 - Quit");
				choice = jin.nextInt();
				switch(choice)
				{
					case 1:
						jin = new Scanner(System.in);
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
						boolean validOption = false;
						if(!acntType.equals("ADMIN"))
						{
							while(!validOption)
							{
								try {
									System.out.println("How much money are they depositing to open your account?");
									System.out.print("$");
									balance = jin.nextDouble();
									admin.create(acntType, firstName, lastName, username, password, balance);
								} catch(ClassCastException cce){
							        System.out.println("Your input is invalid, please try again");
							        validOption = false;
							    } catch(InputMismatchException ime){
							        System.out.println("Your input is invalid, please try again");
							        validOption = false;
							    }
							}
							validOption = true;
						}
						else if(acntType.equals("ADMIN"))
						{
							balance = 0;
							admin.create(acntType, firstName, lastName, username, password, balance);
						}
						break;
					case 2:
						jin = new Scanner(System.in);
						System.out.println("What is the account number you are trying to delete?");
						accountNum = jin.nextInt();
						admin.delete(accountNum);
						break;
					case 3:
						System.out.println("\n");
						admin.viewAllCust();
						System.out.println("\n");
						break;
					case 4:
						System.out.println("\n");
						admin.viewAllTrans();
						System.out.println("\n");
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