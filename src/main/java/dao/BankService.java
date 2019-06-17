package dao;

import java.util.List;

import com.revature.banking.*;

public class BankService {
	private static final BankDao dao = new BankOracle();
	
	public static List<Bankaccounts> getAllAccounts() throws Exception {
		return dao.getAll();
	}
	public static Customer login(int acntNum, String userName, String password) throws Exception{
		return dao.login(acntNum, userName, password);
	}
	public static SuperUser loginu(int acntNum, String userName, String password) throws Exception{
		return dao.loginu(acntNum, userName, password);
	}
}