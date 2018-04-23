package com.att.biq.db;
/**
 * Author: Doron Niv
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

public class Employees {
	private String SELECT_ALL_EMPLOYEE_BY_CHAIN_ID = "SELECT * FROM employees join stores on employees.stores_id = stores.stores_id where stores.group_id = ? or employees.group_id = ?";
	private String ADD_STORE_EMPLOYEE = "INSERT INTO employees (first_name, last_name, age, stores_id) VALUES (?,?,?,?)";
	private String ADD_GROUP_EMPLOYEE = "INSERT INTO employees (first_name, last_name, age, group_id) VALUES (?,?,?,?)";

	private String firstName = "";
	private String lastName = "";
	private int age = 0;
	private Integer stores_id = null;
	private Integer groups_id = null;

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;

	public Employees(Connection con) {
		this.connection = con;
	}

	public void addNewEmployee() throws SQLException {
		setGroupOrStoreEmployee();
		setEmployeeDetails();
		addEmployeeToTable();

	}

	private void setGroupOrStoreEmployee() throws SQLException {
		boolean flag = false;
		Scanner sc = new Scanner(System.in);
		while (!flag) {
			System.out.println("The new employee work in: (please select 1 or 2)");
			System.out.println("1. Store");
			System.out.println("2. Group");
			int ans = sc.nextInt();
			if (ans == 1) {
				flag = true;
				new Stores(connection).getAllStores();
				System.out.println("------------------------------------------------------");
				System.out.println(
						"Please select the store id which the employee belong to from the above stores list (stores_id: ...)");
				stores_id = sc.nextInt();
			} else if (ans == 2) {
				flag = true;
				new Chains(connection).getAllChains();
				System.out.println("------------------------------------------------------");
				System.out.println(
						"Please select the group id which the employee belong to from the above group list (group_id: ...)");
				stores_id = sc.nextInt();
			} else {
				System.out.println("The new employee work in: (please select 1 or 2)");
			}
		}
	}

	private void setEmployeeDetails() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please insert employee first name:");
		firstName = sc.nextLine();
		System.out.println("Please insert employee last name:");
		lastName = sc.nextLine();
		System.out.println("Please insert employee age");
		age = sc.nextInt();
	}

	public void addEmployeeToTable() throws SQLException {
		if (stores_id != null) {
			preparedStatement = connection.prepareStatement(ADD_STORE_EMPLOYEE);
		} else {
			preparedStatement = connection.prepareStatement(ADD_GROUP_EMPLOYEE);
		}
		preparedStatement.setString(1,firstName);
		preparedStatement.setString(2,lastName);
		preparedStatement.setInt(3, age);
		if (stores_id != null) {
			preparedStatement.setInt(4, stores_id);
		} else {
			preparedStatement.setInt(4, groups_id);
		}
		preparedStatement.executeUpdate();
	}

	public boolean getEmployeeByChainId(String chainId) throws SQLException {
		boolean flag = false;
		ResultSet rs = null;
		preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEE_BY_CHAIN_ID);
		preparedStatement.setString(1, chainId);
		preparedStatement.setString(2, chainId);
		try {
			rs = preparedStatement.executeQuery();
		} catch (SQLException e) {
			return false;
		}
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
			System.out.println("No results found.");
			System.out.println();

		}
		return true;
	}
}
