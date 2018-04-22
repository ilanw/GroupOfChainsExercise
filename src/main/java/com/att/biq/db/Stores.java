package com.att.biq.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

public class Stores {
	private static int id = 350;
	private String SELECT_STORE_BY_MALL_ID = "SELECT * FROM stores where mall_id = ?";
	private String SELECT_STORE_BY_MALL_GROUP_ID = "SELECT * FROM malls join stores on malls.mall_id = stores.mall_id where mall_group_id = ?";
	private String SELECT_ALL_STORES = "SELECT * FROM stores ";
	private String SELECT_ADDRESS = "SELECT address_id FROM addresses where city_id = ? and street_id = ? and building_No =?";
	private String INSERT_CHAIN_IN_MALL = "INSERT INTO stores (store_name,mall_id,group_id) VALUES (?,?,?)";
	private String INSERT_CHAIN  = "INSERT INTO stores (store_name,address_id,group_id) VALUES (?,?,?)";
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;


	private String store_name="";
	private Integer address_id=null;
	private Integer mall_id = null;
	private Integer groups_id = null;


	public Stores(Connection con) {
		this.connection = con;
	}

	public void addNewStore() throws SQLException {
		setStoreName();
		setStoreGroupID();
		setMallID();
		setStoreAddress();
		addStoreToTable();


	}

	private void setStoreName() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please insert store name:" );
		store_name=sc.nextLine();
	}

	private void setStoreGroupID() throws SQLException {
		new Chains(connection).getAllChains();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please select the group id which the store belong to from the above group list (group_id: ...)");
		groups_id=sc.nextInt();

	}

	private void setMallID() throws SQLException {
		boolean flag = false;
		Scanner sc = new Scanner(System.in);
		while (!flag) {
			System.out.println("Is this store is inside a mall? (y/n)");
			String ans = sc.nextLine();
			if (ans.toLowerCase().equals("y")) {
				flag = true;
				new Malls(connection).getAllMalls();System.out.println("Please select the mall id which the store belong to from the above mall list (mall_id: ...)");
				mall_id=sc.nextInt();
			} else if (ans.toLowerCase().equals("n")) {
				flag = true;
			} else {
				System.out.println("Invalid input");
			}
		}
	}


	private void setStoreAddress() throws SQLException {
		if (mall_id != null) {
			String quarry = "SELECT address_id FROM malls where mall_id = ?";
			preparedStatement = connection.prepareStatement(quarry);
			preparedStatement.setInt(1, mall_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				address_id = rs.getInt("address_id");
			}
		} else {
			Address ad = new Address(connection);
			int city_id = getCityID(ad);
			int street_id = gettreetID(ad);
			int building_No = getBuildingNo();
			preparedStatement = connection.prepareStatement(SELECT_ADDRESS);
			preparedStatement.setInt(1, city_id);
			preparedStatement.setInt(2, street_id);
			preparedStatement.setInt(3, building_No);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				address_id = rs.getInt("address_id");
			} else{
				ad.setNewAddress(city_id,street_id,building_No);
				rs = preparedStatement.executeQuery();
				rs.next();
				address_id = rs.getInt("address_id");
			}
		}
	}


	private int getCityID(Address ad) throws SQLException {
		ad.getAllCities();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please select store city address: (city_id: ...)");
		return sc.nextInt();
	}

	private int gettreetID(Address ad) throws SQLException {
		ad.getAllStreets();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please select store street address: (street_id: ...)");
		return sc.nextInt();
	}

	public int getBuildingNo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please insert building number");
		return sc.nextInt();
	}


	public void addStoreToTable() throws SQLException {
		if (mall_id!=null) {
			preparedStatement = connection.prepareStatement(INSERT_CHAIN_IN_MALL);
		} else {
			preparedStatement = connection.prepareStatement(INSERT_CHAIN);
		}
		preparedStatement.setString(1,store_name);
		if (mall_id!=null) {
			preparedStatement.setInt(2, mall_id);
		} else {
			preparedStatement.setInt(2, address_id);
		}
		preparedStatement.setInt(3, groups_id);
		preparedStatement.executeUpdate();
	}

	public void getStoresByMallId(String mallId) throws SQLException {
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

	public void getStoresByMallGroupId(String mallGroupId) throws SQLException {
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
