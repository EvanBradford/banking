package dao;

import java.util.List;

import com.revature.banking.*;

public interface BankDao {
	List<Bankaccounts> getAll() throws Exception;
	List<String> getAllTrans() throws Exception;
	void insert(String acntType, String firstName, String lastName, String userName, String password, double balance) throws Exception;
	void delete(int acntNum) throws Exception;
	Customer login(int acntNum, String userName, String password) throws Exception;
	SuperUser loginu(int acntNum, String userName, String password) throws Exception;
	void deposit(int acntNum, double balance, double amount) throws Exception;
	void withdraw(int acntNum, double balance, double amount) throws Exception;
	void transferFunds(int acntNum1,int acntNum2, double amount, double balance) throws Exception;
}