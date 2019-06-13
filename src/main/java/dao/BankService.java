package dao;

import java.util.List;

import com.revature.banking.*;

public class BankService {
	private static final BankDao dao = new BankOracle();
	
	public static List<User> getAllAccounts() throws Exception {
		return dao.getAll();
	}
}