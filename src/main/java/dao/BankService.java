package dao;

import java.util.List;

import com.revature.banking.*;

public class BankService {
	private static final BankDao dao = new BankOracle();
	
	public static List<Bankaccounts> getAllAccounts() throws Exception {
		return dao.getAll();
	}
	public static List<String> getAllTrans() throws Exception
	{
		return dao.getAllTrans();
	}
	public static Customer login(int acntNum, String userName, String password) throws Exception{
		return dao.login(acntNum, userName, password);
	}
	public static SuperUser loginu(int acntNum, String userName, String password) throws Exception{
		return dao.loginu(acntNum, userName, password);
	}
	public static void delete(int acntNum) throws Exception{
		dao.delete(acntNum);
		return;
	}
	public static void insert(String acntType, String firstName, String lastName, String userName, String password, double balance) throws Exception {
		dao.insert(acntType, firstName, lastName, userName, password, balance);
		return;
	}
	public static void deposit(int acntNum, double balance, double amount) throws Exception
	{
		dao.deposit(acntNum, balance, amount);
		return;
	}
	public static void withdraw(int acntNum, double balance, double amount) throws Exception
	{
		dao.withdraw(acntNum, balance, amount);
		return;
	}
	public static void transferFunds(int acntNum1,int acntNum2, double amount, double balance) throws Exception
	{
		dao.transferFunds(acntNum1, acntNum2, amount, balance);
		return;
	}
}