package com.att.biq.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class Chains
{
	private int id = 100;
	private String SELECT_CHAINS_BY_NAME = "SELECT chain_id, chain_name FROM Chains WHERE chain_name = ?";
	private String INSERT_CHAIN = "INSERT INTO Chains VALUES (?, ?,?)";
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;

	public Chains(Connection con)
	{
		this.connection = con;
	}

	public void createChain(String name, String type) throws SQLException
	{
		preparedStatement = connection.prepareStatement(INSERT_CHAIN);
		preparedStatement.setInt(1, ++id);
		preparedStatement.setString(2, name);
		preparedStatement.setString(3, type);
		preparedStatement.executeUpdate();
	}

	public void getChain(String name) throws SQLException
	{
		preparedStatement = connection.prepareStatement(SELECT_CHAINS_BY_NAME);
		preparedStatement.setString(1, name);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next())
		{
			String chain_id = rs.getString("chain_id");
			String chain_name = rs.getString("chain_name");
			System.out.println("userid : " + chain_id);
			System.out.println("username : " + chain_name);
		}
	}
}
