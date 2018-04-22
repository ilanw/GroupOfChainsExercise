package com.att.biq.db;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

public class Menu extends DbBase {

	public Menu() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void runApplication() throws SQLException, IOException {
		Scanner sc = new Scanner(System.in);
		String msg = "";
		while (!msg.equals("0")) {
			System.out.println("Group of chains application menu:");
			System.out.println("A. To create new chain, please press 1");
			System.out.println("B. To add a store to a chain, please press 2");
			System.out.println("C. To add an employee to a chain/group managment, please press 3");
			System.out.println("D. To present all shops that are in a certain Shopping Mall, please press 4");
			System.out.println("E. To present all shops that are in a certain Shopping Mall Group, please press 5");
			System.out.println("F. To present all employees of a certain chain, please press 6");
			System.out.println("F. To present all details of a shope, please press 7");

			System.out.println("G. To exit, please press 0");

			msg = sc.nextLine();

			if (!msg.equals("0")) {

			}
			Connection con = connect();
			switch (msg) {
			case "1": {
				new Chains(con).getAllChains();
				System.out
						.println("Please insert chain name and chain parent name (leave it empty if not applicable) :");
				String name = sc.nextLine();
				String parent = sc.nextLine();
				// TODO: Add runInputValidation
				new Chains(con).createChain(name, parent);
				break;
			}
			case "2": {
				new Stores(con).addNewStore();
				new Chains(con).getAllChains();
				System.out.println("Please insert store name and chain id :");
				String name = sc.nextLine();
				String type = sc.nextLine();
				// TODO: Add runInputValidation
				break;
			}
			case "3": {
				new Employees(con).addNewEmployee();
				// TODO: Add runInputValidation
				break;
			}
			case "4": {
				new Malls(con).getAllMalls();
				System.out.println("Please insert Mall Id :");
				String mall = sc.nextLine();

				// TODO: Add runInputValidation
				new Stores(con).getStoresByMallId(mall);
				break;
			}
			case "5": {
				new Malls(con).getAllMallsGroup();
				System.out.println("Please insert Mall Group Id :");
				String mallGroupId = sc.nextLine();

				// TODO: Add runInputValidation
				new Stores(con).getStoresByMallGroupId(mallGroupId);
				break;
			}
			case "6": {
				new Chains(con).getAllChains();
				System.out.println("Please insert chain Id :");
				String chainId = sc.nextLine();

				// TODO: Add runInputValidation
				new Employees(con).getEmployeeByChainId(chainId);
				break;
			}
			case "7": {
				new Stores(con).getAllStores();
				System.out.println("Please insert shope Id :");
				String storeId = sc.nextLine();

				// TODO: Add runInputValidation
				new Stores(con).getAllStoreDetails(storeId);
				break;
			}
			}

		}
		sc.close();
	}
}
