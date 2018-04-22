package com.att.biq.db;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

public class Menu extends DbBase {

	public Menu() throws IOException {
		super();
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

			Connection con = connect();
			switch (msg) {
			case "1": {
				new Chains(con).getAllChains();
				System.out.println("Please insert chain name: ");
				String name = sc.nextLine();
				System.out.println("Please insert chain parent name (leave it empty if not applicable) :");
				String parent = sc.nextLine();
				System.out.println("Please insert business type: ");
				String type = sc.nextLine();
				boolean res = new Chains(con).createChain(name, parent, type);
				if (!res) {
					System.out.println();
					System.out.println("Something went wrong. Execution failed. Please try again...");
					System.out.println();
				}

				break;
			}
			case "2": {
				new Stores(con).addNewStore();
				System.out.println("Please insert chain id :");
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
				boolean res = new Stores(con).getStoresByMallId(mall);
				if (!res) {
					System.out.println();
					System.out.println("Something went wrong. Query failed. Please try again...");
					System.out.println();
				}

				break;
			}
			case "5": {
				new Malls(con).getAllMallsGroup();
				System.out.println("Please insert Mall Group Id :");
				String mallGroupId = sc.nextLine();
				boolean res = new Stores(con).getStoresByMallGroupId(mallGroupId);
				if (!res) {
					System.out.println();
					System.out.println("Something went wrong. Query failed. Please try again...");
					System.out.println();
				}
				break;
			}
			case "6": {
				new Chains(con).getAllChains();
				System.out.println("Please insert chain Id :");
				String chainId = sc.nextLine();
				boolean res = new Employees(con).getEmployeeByChainId(chainId);
				if (!res) {
					System.out.println();
					System.out.println("Something went wrong. Query failed. Please try again...");
					System.out.println();
				}
				break;
			}
			case "7": {
				new Stores(con).getAllStores();
				System.out.println("Please insert shope Id :");
				String storeId = sc.nextLine();

				new Stores(con).getAllStoreDetails(storeId);
				break;
			}
			default: {
				System.out.println("Wrong input inserted. Please try again...");
				System.out.println();
				break;
			}
			}

		}
		sc.close();
	}
}
