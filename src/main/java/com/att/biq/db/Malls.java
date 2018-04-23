package com.att.biq.db;

/**
 * Author: Ilan Wallerstein
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class Malls {
	private String SELECT_ALL_MALLS = "SELECT * FROM malls ";
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;

	public Malls(Connection con) {
		this.connection = con;
	}

	// select all malls
	public void getAllMalls() throws SQLException {
		ResultSet rs = null;
		boolean flag = false;
		System.out.println("Current exist malls are: ");
		preparedStatement = connection.prepareStatement(SELECT_ALL_MALLS);
		try {
			rs = preparedStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println(e);
		}
		while (rs.next()) {
			flag = true;
			String mall_id = rs.getString("mall_id");
			String mall_name = rs.getString("mall_name");
			System.out.print("- " + " Mall id : " + mall_id);
			System.out.println("     | Mall name : " + mall_name);
		}
		if (!flag) {
			System.out.println("No results found. Please try again from main menu...");
			System.out.println();

		}
		System.out.println();
	}

	// select all malls group from malls table
	public void getAllMallsGroup() throws SQLException {
		ResultSet rs = null;
		boolean flag = false;
		System.out.println("Current exist malls group are: ");
		preparedStatement = connection.prepareStatement(SELECT_ALL_MALLS);
		try {
			rs = preparedStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println(e);
		}
		while (rs.next()) {
			flag = true;
			String mall_group_id = rs.getString("mall_group_id");
			System.out.println("- " + " Mall group id : " + mall_group_id);
		}
		if (!flag) {
			System.out.println("No results found. Please try again from main menu...");
			System.out.println();

		}
		System.out.println();
	}

}
