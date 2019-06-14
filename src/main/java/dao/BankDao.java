package dao;

import java.util.List;

import com.revature.banking.*;

public interface BankDao {
	List<Bankaccounts> getAll() throws Exception;
	void insert(Bankaccounts a) throws Exception;
}