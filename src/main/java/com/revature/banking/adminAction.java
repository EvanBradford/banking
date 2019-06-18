package com.revature.banking;

public interface adminAction {
	void viewAllCust();
	void viewAllTrans();
	void create(String acntType, String firstName, String lastName, String userName, String password, double balance);
	void delete(int acntNum);
	SuperUser loginu(int acntNum, String username, String password);
}