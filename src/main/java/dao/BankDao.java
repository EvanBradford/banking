package dao;

import java.util.List;

import com.revature.banking.*;

public interface BankDao {
	List<User> getAll() throws Exception;
	void insert(User a) throws Exception;
}