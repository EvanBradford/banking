package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.banking.*;
import util.ConnectionUtil;

public class BankOracle implements BankDao {

	private static final Logger log = LogManager.getLogger(BankOracle.class);

	public List<Bankaccounts> getAll() throws Exception {
		Connection con = ConnectionUtil.getConnection();

		if (con == null) {
			log.error("Connection was null");
			throw new Exception("Unable to connect to database");
		}

		List<Bankaccounts> list;
		
		try {			
			String sql = "select * from BANKACCOUNTS";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			list = new LinkedList<Bankaccounts>();
			
			while(rs.next()){
				if(rs.getString("acntType").equals("ADMIN"))
				{
					list.add(new SuperUser(rs.getInt("acntNum"), rs.getString("acntType"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("password"), rs.getDouble("balance")));
				}
				else
				{
					list.add(new Customer(rs.getInt("acntNum"), rs.getString("acntType"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("password"), rs.getDouble("balance")));
				}
			}
		}catch (SQLException e) {
			log.error("Unable to execute sql query", e);
			throw new Exception("Unable to connect to database");
		}
		return list;
	}

	public void insert(String acntType, String firstName, String lastName, String userName, String password, double balance) throws Exception {
		// TODO Insert
		Connection con = ConnectionUtil.getConnection();

		if (con == null) {
			log.error("Connection was null");
			throw new Exception("Unable to connect to database");
		}
		try {
			String sql = "call create_account(?, ?, ?, ?, ?, ?, ?)";
			CallableStatement ps = con.prepareCall(sql);
			ps.registerOutParameter(1, Types.INTEGER);
			ps.setString(2, acntType);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5, userName);
			ps.setString(6, password);
			ps.setDouble(7, balance);
			ps.executeUpdate();
			int id = ps.getInt(1);
			System.out.println("Successfully Added to database: ");
			System.out.println(id);
		} catch (SQLException e) {
			log.error("Unable to execute sql query", e);
			throw new Exception("Unable to connect to database");
		}
	}
	
	public Customer login(int acntNum, String userName, String password) throws Exception{
		Customer tmp = new Customer();
		// TODO login function
		Connection con = ConnectionUtil.getConnection();

		if (con == null) {
			log.error("Connection was null");
			throw new Exception("Unable to connect to database");
		}
		try {
			String sql = "select * from BANKACCOUNTS";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				if(rs.getString("Username").equals(userName) && rs.getString("password").equals(password) && (rs.getInt("acntNum") == acntNum))
				{
					if(rs.getString("acntType").equals("CUSTOMER"))
					{
						tmp = new Customer(rs.getInt("acntNum"), rs.getString("acntType"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("password"), rs.getDouble("balance"));
					}
				}
			}
		} catch (SQLException e) {
			log.error("Unable to execute sql query", e);
			throw new Exception("Unable to connect to database");
		}
		return tmp;
	}
	
	public SuperUser loginu(int acntNum, String userName, String password) throws Exception{
		SuperUser tmp = new SuperUser();
		// TODO login function
		Connection con = ConnectionUtil.getConnection();

		if (con == null) {
			log.error("Connection was null");
			throw new Exception("Unable to connect to database");
		}
		try {
			String sql = "select * from BANKACCOUNTS";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				if(rs.getString("Username").equals(userName) && rs.getString("password").equals(password) && (rs.getInt("acntNum") == acntNum))
				{
					if(rs.getString("acntType").equals("ADMIN"))
					{
						tmp = new SuperUser(rs.getInt("acntNum"), rs.getString("acntType"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("password"), rs.getDouble("balance"));
					}
				}
			}
		} catch (SQLException e) {
			log.error("Unable to execute sql query", e);
			throw new Exception("Unable to connect to database");
		}
		return tmp;
	}

	@Override
	public void delete(int acntNum) throws Exception
	{
		// TODO Delete function
		Connection con = ConnectionUtil.getConnection();
		if  (con == null) {
			log.error("Connection was null");
			throw new Exception("Unable to connect to database");
		}
		try {
			String sql = "DELETE FROM BANKACCOUNTS WHERE ACNTNUM =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, acntNum);
			int row = ps.executeUpdate();
			System.out.println(row);
		} catch (SQLException e) {
			log.error("Unable to execute sql query", e);
			throw new Exception("Unable to connect to database");
		}
	}
	
	@Override
	public void updateAccount(int acntNum, double balance) throws Exception
	{
		// TODO updates balance
		Connection con = ConnectionUtil.getConnection();
		if (con == null) {
			log.error("Connection was null");
			throw new Exception("Unable to connect to database");
		}
		try {
			String sql = "UPDATE BANKACCOUNTS SET BALANCE = ? WHERE ACNTNUM = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, balance);
			ps.setInt(2, acntNum);
			ps.execute();
			System.out.println("Balance Updated");
		} catch (SQLException e) {
			log.error("Unable to execute sql query", e);
			throw new Exception("Unable to connect to database");
		}
	}
}