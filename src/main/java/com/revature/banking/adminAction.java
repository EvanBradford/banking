package com.revature.banking;

public interface adminAction {
	void viewAllCust();
	void viewAllTrans();
	void create();
	void delete();
	SuperUser loginu(int acntNum, String username, String password);
}