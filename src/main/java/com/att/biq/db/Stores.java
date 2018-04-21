package com.att.biq.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class Stores {
	private static int id = 350;
	private String SELECT_STORE_BY_MALL_ID = "SELECT * FROM stores where mall_id = ?";
	private String SELECT_STORE_BY_MALL_GROUP_ID = "SELECT * FROM malls join stores on malls.mall_id = stores.mall_id where mall_group_id = ?";
	private String SELECT_ALL_STORES = "SELECT * FROM stores ";
	private String INSERT_CHAIN = "INSERT INTO stores VALUES (?,?,?,?)";
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;

	public Stores(Connection con) {
		this.connection = con;
	}

	public void createStore(String name, String parent) throws SQLException {
		/*
		 * int parentid = 0; preparedStatement =
		 * connection.prepareStatement(INSERT_CHAIN); preparedStatement.setInt(1, ++id);
		 * 
		 * if (parent != null) { PreparedStatement tempStatement = connection
		 * .prepareStatement("select parent_group_id FROM groups where group_name=?");
		 * tempStatement.setString(1, parent); ResultSet rs =
		 * tempStatement.executeQuery(); while (rs.next()) { parentid = rs.getInt(1); }
		 * preparedStatement.setLong(2, parentid); } else preparedStatement.setString(2,
		 * null);
		 * 
		 * preparedStatement.setString(3, name); preparedStatement.setString(4, ""); try
		 * { preparedStatement.executeUpdate(); } catch (SQLException e) { System.out.
		 * println("Something went wrong. Execution failed. Please try again...");
		 * System.out.println(); return; // TODO: handle exception }
		 */
	}

	public void getStorseByMallId(String mallId) throws SQLException {
		boolean flag = false;
		preparedStatement = connection.prepareStatement(SELECT_STORE_BY_MALL_ID);
		preparedStatement.setString(1, mallId);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			flag = true;
			String store_id = rs.getString("stores_id");
			String store_name = rs.getString("store_name");
			System.out.print("store_id : " + store_id);
			System.out.println("    | store_name : " + store_name);
		}
		System.out.println();
		if (!flag) {
			System.out.println("No results found. Please try again from main menu...");
			System.out.println();
			return;
		}
	}

	public void getStorseByMallGroupId(String mallGroupId) throws SQLException {
		boolean flag = false;
		preparedStatement = connection.prepareStatement(SELECT_STORE_BY_MALL_GROUP_ID);
		preparedStatement.setString(1, mallGroupId);
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			flag = true;
			String store_id = rs.getString("stores_id");
			String store_name = rs.getString("store_name");
			System.out.print("store_id : " + store_id);
			System.out.println("    | store_name : " + store_name);
		}
		System.out.println();
		if (!flag) {
			System.out.println("No results found. Please try again from main menu...");
			System.out.println();
			return;
		}
	}

	public void getAllStores() throws SQLException {
		int index = 1;
		System.out.println("Current exist stores are: ");
		preparedStatement = connection.prepareStatement(SELECT_ALL_STORES);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {

			String stores_id = rs.getString("stores_id");
			String store_name = rs.getString("store_name");
			String mall_id = rs.getString("mall_id");
			System.out.print(index++ + ". Store id : " + stores_id);
			System.out.print("     | Store name : " + store_name);
			System.out.println("     | Mall id : " + mall_id);
		}
		System.out.println();
	}

	public void getAllStoreDetails(String storeId) {
		// TODO Auto-generated method stub
		
	}

}
