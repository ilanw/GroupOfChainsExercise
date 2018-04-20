package com.att.biq.db;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main extends DbBase {

	public Main() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException, SQLException {
	//	runApplication();

	}

	private void runApplication() throws SQLException {
		Scanner sc = new Scanner(System.in);
		String msg = "";
		while (!msg.equals("0")) {
			System.out.println("Group of chains application menu:");
			System.out.println("1. To create new chain, please press 1");
			System.out.println("2. To add a store to a chain, please press 2");
			System.out.println("3. To add an employee to a chain/group managment, please press 3");
			System.out.println("4. To exit, please press 0");

			msg = sc.nextLine();

			if (!msg.equals("0")) {

			}
			switch (msg) {
			case "1": {
				System.out.println("Please insert chian name and type :");
				String name = sc.nextLine();
				String type = sc.nextLine();
				// TODO: Add runInputValidation
				new Chains(getConn()).createChain(name, type);
			}
			case "2": {
				System.out.println("Please insert store name and type :");
				String name = sc.nextLine();
				String type = sc.nextLine();
				// TODO: Add runInputValidation
				new Chains(getConn()).createChain(name, type);
			}
			}

		}
		sc.close();
	}
}
