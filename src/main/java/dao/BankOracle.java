package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.banking.*;
import util.ConnectionUtil;

public class BankOracle implements BankDao {
	private static final Logger log = LogManager.getLogger(BankOracle.class);
	private static final DecimalFormat df = new DecimalFormat();

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
			System.out.print("Your account number is: ");
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
	public void deposit(int acntNum, double balance, double amount) throws Exception
	{
		//updates balance
		Date date = new Date();
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
			df.setMinimumFractionDigits(2);
			df.setMaximumFractionDigits(2);
			String trans = date + " Account #" + acntNum + " Deposited $" + df.format(amount);
		
			sql = "call create_trans(?, ?, ?, ?)";
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, trans);
			cs.setDouble(3, amount);
			cs.setInt(4, acntNum);
			cs.executeUpdate();
			int id = cs.getInt(1);
			System.out.println("Successfully Added transaction to database");
			System.out.print("Your transaction number is: ");
			System.out.println(id);
			
		} catch (SQLException e) {
			log.error("Unable to execute sql query", e);
			throw new Exception("Unable to connect to database");
		}
	}
	@Override
	public void withdraw(int acntNum, double balance, double amount) throws Exception
	{
		// TODO updates balance
		Date date = new Date();
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
			df.setMinimumFractionDigits(2);
			df.setMaximumFractionDigits(2);
			String trans = date + " Account #" + acntNum + " Withdrew $" + df.format(amount);
			
			sql = "call create_trans(?, ?, ?, ?)";
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, trans);
			cs.setDouble(3, amount);
			cs.setInt(4, acntNum);
			cs.executeUpdate();
			int id = cs.getInt(1);
			System.out.println("Successfully Added transaction to database");
			System.out.print("Your transaction number is: ");
			System.out.println(id);
		} catch (SQLException e) {
			log.error("Unable to execute sql query", e);
			throw new Exception("Unable to connect to database");
		}
	}
	@Override
	public void transferFunds(int acntNum1,int acntNum2, double amount, double balance) throws Exception
	{
		double transfer = amount;
		// TODO updates balances
		Date date = new Date();
		Connection con = ConnectionUtil.getConnection();
		Customer tmp = new Customer();
		if (con == null) {
			log.error("Connection was null");
			throw new Exception("Unable to connect to database");
		}
		try {
			//Gets current balance of transferee
			String sql = "select * from BANKACCOUNTS where acntnum=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, acntNum2);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				if((rs.getInt("acntNum") == acntNum2))
				{
					tmp = new Customer(rs.getInt("acntNum"), rs.getString("acntType"), rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("password"), rs.getDouble("balance"));
				}
			}
			//calculates new balance for account 2
			amount += tmp.getBalance();
			amount = Math.round(amount * 100.0) / 100.0;
			//Updates first account with the lower balance
			sql = "UPDATE BANKACCOUNTS SET BALANCE = ? WHERE ACNTNUM = ?";
			ps = con.prepareStatement(sql);
			ps.setDouble(1, balance);
			ps.setInt(2, acntNum1);
			ps.execute();
			
			//updates second account with higher balance
			sql = "UPDATE BANKACCOUNTS SET BALANCE = ? WHERE ACNTNUM = ?";
			ps = con.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setInt(2, acntNum2);
			ps.execute();
			System.out.println("Balances Updated");
			System.out.println("New Balance: $" + balance);
			df.setMinimumFractionDigits(2);
			df.setMaximumFractionDigits(2);
			String trans = date + " Account #" + acntNum1 + " Transferred $" + df.format(transfer) + " to Account #" + acntNum2;
			
			sql = "call create_trans(?, ?, ?, ?)";
			CallableStatement cs = con.prepareCall(sql);
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, trans);
			cs.setDouble(3, transfer);
			cs.setInt(4, acntNum1);
			cs.executeUpdate();
			int id = cs.getInt(1);
			System.out.println("Successfully Added transaction to database");
			System.out.print("Your transaction number is: ");
			System.out.println(id);
		} catch (SQLException e) {
			log.error("Unable to execute sql query", e);
			throw new Exception("Unable to connect to database");
		}
	}
	public List<String> getAllTrans() throws Exception
	{
		Connection con = ConnectionUtil.getConnection();

		if (con == null) {
			log.error("Connection was null");
			throw new Exception("Unable to connect to database");
		}

		List<String> list;
		
		try {			
			String sql = "select TRANS from TRANSACTIONS";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			list = new LinkedList<String>();
			
			while(rs.next()){
				list.add(rs.getString("TRANS"));
			}
		}catch (SQLException e) {
			log.error("Unable to execute sql query", e);
			throw new Exception("Unable to connect to database");
		}
		return list;
	}
}