package com.att.biq.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class Malls {
	private String SELECT_ALL_MALLS = "SELECT * FROM malls ";
	private String SELECT_ALL_MALLS_GROUP = "SELECT mall_group_id FROM malls ";
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;

	public Malls(Connection con) {
		this.connection = con;
	}
	//select all malls
	public void getAllMalls() throws SQLException {
		int index = 1;
		System.out.println("Current exist malls are: ");
		preparedStatement = connection.prepareStatement(SELECT_ALL_MALLS);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {

			String mall_id = rs.getString("mall_id");
			String mall_name = rs.getString("mall_name");
			System.out.print(index++ + ". Mall id : " + mall_id);
			System.out.println("     | Mall name : " + mall_name);
		}
		System.out.println();
	}
	//select all malls group from malls table 
	public void getAllMallsGroup() throws SQLException {
		int index = 1;
		System.out.println("Current exist malls group are: ");
		preparedStatement = connection.prepareStatement(SELECT_ALL_MALLS);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			String mall_group_id = rs.getString("mall_group_id");
			System.out.println(index++ + ". Mall group id : " + mall_group_id);		
		}
		System.out.println();
	}
	
}
