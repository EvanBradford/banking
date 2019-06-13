package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.banking.*;
import util.ConnectionUtil;

public class BankOracle implements BankDao {

	private static final Logger log = LogManager.getLogger(BankOracle.class);

	public List<User> getAll() throws Exception {
		Connection con = ConnectionUtil.getConnection();

		if (con == null) {
			log.error("Connection was null");
			throw new Exception("Unable to connect to database");
		}

		List<User> list;
		
		try {			
			String sql = "select * from BANKACCOUNTS";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			list = new LinkedList<User>();
			
			while(rs.next()){
				;//list.add(new User(rs.getInt("id"), rs.getString("name"), rs.getInt("safty")));
			}
		} catch (SQLException e) {
			log.error("Unable to execute sql query", e);
			throw new Exception("Unable to connect to database");
		}

		return list;
	}

	public void insert(User a) throws Exception {
		// TODO Auto-generated method stub
		
	}

}