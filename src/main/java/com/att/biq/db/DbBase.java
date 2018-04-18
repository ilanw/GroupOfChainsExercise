package com.att.biq.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public abstract class DbBase
{

	MysqlDataSource ds;
	Connection conn = null;
	Statement stmt = null;

	public DbBase() throws IOException
	{
		ds = new MysqlDataSource();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("properties.xml");
		Properties dbProps = new Properties();
		dbProps.loadFromXML(is);
		ds.setServerName(dbProps.getProperty("serverName"));
		ds.setPortNumber(Integer.parseInt(dbProps.getProperty("serverPort")));
		ds.setDatabaseName(dbProps.getProperty("dbName"));
		ds.setUser(dbProps.getProperty("user"));
		ds.setPassword(dbProps.getProperty("password"));
	}

	public Connection getConn()
	{
		return conn;
	}

	public Connection connect() throws SQLException, IOException
	{
		return (Connection) ds.getConnection();
	}

}
