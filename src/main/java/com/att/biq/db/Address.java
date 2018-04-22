package com.att.biq.db;

import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Address {
    private String SELECT_ALL_ADDRESS = "SELECT * FROM address ";
    private String SELECT_ALL_CITIES = "SELECT * FROM cities ";
    private String SELECT_ALL_STREETS = "SELECT * FROM cities ";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    public Address(Connection con) {
        this.connection = con;
    }

    public void getAllAddress() throws SQLException {
        int index = 1;
        System.out.println("Current exist malls are: ");
        preparedStatement = connection.prepareStatement(SELECT_ALL_ADDRESS);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int address_id = rs.getInt("address_id");
            int city_id = rs.getInt("city_id");
            int street_id = rs.getInt("street_id");
            int zip_code = rs.getInt("zip_code");
            int building_No = rs.getInt("building_No");

            System.out.print(index++ + ". Address id : " + address_id);
            System.out.println("     | City id : " + city_id);
            System.out.println("     | Street id : " + street_id);
            System.out.println("     | Zip code : " + zip_code);
            System.out.println("     | Building number : " + building_No);
        }
        System.out.println();
    }

    public void getAllCitys() throws SQLException {
        int index = 1;
        System.out.println("Current exist malls are: ");
        preparedStatement = connection.prepareStatement(SELECT_ALL_ADDRESS);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int address_id = rs.getInt("address_id");
            int city_id = rs.getInt("city_id");
            int street_id = rs.getInt("street_id");
            int zip_code = rs.getInt("zip_code");
            int building_No = rs.getInt("building_No");

            System.out.print(index++ + ". Address id : " + address_id);
            System.out.println("     | City id : " + city_id);
            System.out.println("     | Street id : " + street_id);
            System.out.println("     | Zip code : " + zip_code);
            System.out.println("     | Building number : " + building_No);
        }
        System.out.println();
    }

}

