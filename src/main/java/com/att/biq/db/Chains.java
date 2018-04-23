package com.att.biq.db;

/**
 * Author: Guy Bitan, Ilan Wallerstein
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class Chains {
	private String SELECT_CHAINS_BY_NAME = "SELECT group_id, group_name FROM Groups WHERE group_name = ?";
	private String SELECT_ALL_CHAINS = "SELECT * FROM Groups ";
	private String INSERT_CHAIN = "INSERT INTO Groups VALUES (NULL,?,?,?)";
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;

	public Chains(Connection con) {
		this.connection = con;
	}

	// Create new chain
	public boolean createChain(String name, String parent, String type) throws SQLException {
		int parentid = 0;
		ResultSet rs = null;
		preparedStatement = connection.prepareStatement(INSERT_CHAIN);

		if (parent != null) {
			PreparedStatement tempStatement = connection
					.prepareStatement("select group_id FROM groups where group_name=?");
			tempStatement.setString(1, parent);
			try {
				rs = tempStatement.executeQuery();
			} catch (SQLException e) {
				return false;
			}

			while (rs.next()) {
				parentid = rs.getInt(1);
			}
			preparedStatement.setLong(1, parentid);
		} else
			preparedStatement.setString(1, null);

		preparedStatement.setString(2, name);
		preparedStatement.setString(3, type);
		try {
			preparedStatement.executeUpdate();
			System.out.println("Creation succeded...");
			System.out.println();
		} catch (SQLException e) {
			System.out.println("Something went wrong. Execution failed. Please try again...");
			System.out.println();

			// TODO: handle exception
		}
		return true;
	}

	// Get all chains
	public void getAllChains() throws SQLException {
		boolean flag = false;
		ResultSet rs = null;
		System.out.println("Current exist groups are: ");
		preparedStatement = connection.prepareStatement(SELECT_ALL_CHAINS);
		try {
			rs = preparedStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println(e);
		}

		while (rs.next()) {
			flag = true;
			String group_id = rs.getString("group_id");
			String group_name = rs.getString("group_name");
			String parent_id = rs.getString("parent_group_id");
			System.out.print("- " + "Chain id : " + group_id);
			System.out.print("     | Chain name : " + group_name);
			System.out.println("     | Chain parent id : " + parent_id);
		}
		if (!flag) {
			System.out.println("No results found. Please try again from main menu...");
			System.out.println();

		}
	}
}
