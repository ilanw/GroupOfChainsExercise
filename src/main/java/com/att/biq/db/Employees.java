package com.att.biq.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class Employees {
	private String SELECT_ALL_EMPLOYEE_BY_CHAIN_ID = "SELECT * FROM employees join stores on employees.stores_id = stores.stores_id where stores.group_id = ? or employees.group_id = ?";
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;

	public Employees(Connection con) {
		this.connection = con;
	}
	// select all malls

	public void getEmployeeByChainId(String chainId) throws SQLException {
		boolean flag = false;
		preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEE_BY_CHAIN_ID);
		preparedStatement.setString(1, chainId);
		preparedStatement.setString(2, chainId);

		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			flag = true;
			String employee_id = rs.getString("employee_id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			System.out.print("Store id : " + employee_id);
			System.out.print("    | First name : " + first_name);
			System.out.println("    | Last name : " + last_name);
		}
		System.out.println();
		if (!flag) {
			System.out.println("No results found. Please try again from main menu...");
			System.out.println();
			return;
		}
	}
}
