package com.att.biq.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class Chains {
	private static int id = 325;
	private String SELECT_CHAINS_BY_NAME = "SELECT group_id, group_name FROM Groups WHERE group_name = ?";
	private String SELECT_ALL_CHAINS = "SELECT * FROM Groups ";
	private String INSERT_CHAIN = "INSERT INTO Groups VALUES (?,?,?,?)";
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;

	public Chains(Connection con) {
		this.connection = con;
	}

	public void createChain(String name, String parent) throws SQLException {
		int parentid = 0;
		preparedStatement = connection.prepareStatement(INSERT_CHAIN);
		preparedStatement.setInt(1, ++id);

		if (parent != null) {
			PreparedStatement tempStatement = connection
					.prepareStatement("select parent_group_id FROM groups where group_name=?");
			tempStatement.setString(1, parent);
			ResultSet rs = tempStatement.executeQuery();
			while (rs.next()) {
				parentid = rs.getInt(1);
			}
			preparedStatement.setLong(2, parentid);
		} else
			preparedStatement.setString(2, null);

		preparedStatement.setString(3, name);
		preparedStatement.setString(4, "");
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Something went wrong. Execution failed. Please try again...");
			System.out.println();
			id++;
			return;
			// TODO: handle exception
		}
	}

	public void getChain(String name) throws SQLException {
		preparedStatement = connection.prepareStatement(SELECT_CHAINS_BY_NAME);
		preparedStatement.setString(1, name);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			String group_id = rs.getString("group_id");
			String group_name = rs.getString("group_name");
			System.out.println("userid : " + group_id);
			System.out.println("username : " + group_name);
		}
	}

	public void getAllChains() throws SQLException {
		int index = 1;
		System.out.println("Current exist groups are: ");
		preparedStatement = connection.prepareStatement(SELECT_ALL_CHAINS);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {

			String group_id = rs.getString("group_id");
			String group_name = rs.getString("group_name");
			String parent_id = rs.getString("parent_group_id");
			System.out.print(index++ + ". Chain id : " + group_id);
			System.out.print("     | Chain name : " + group_name);
			System.out.println("     | Chain parent id : " + parent_id);
		}
	}
}
