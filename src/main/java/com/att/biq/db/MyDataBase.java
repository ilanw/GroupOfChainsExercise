package com.att.biq.db;

import java.io.IOException;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class MyDataBase extends DbBase
{

	public MyDataBase() throws IOException
	{
		super();
	}

	public static void main(String[] args) throws IOException, SQLException
	{
		MyDataBase myDb = new MyDataBase();
		Connection con = myDb.connect();
		Chains chain = new Chains(con);
		chain.getChain("Fox");

	}

}
