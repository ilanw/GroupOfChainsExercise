package com.att.biq.db;

import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Address {
    private String SELECT_ALL_ADDRESS = "SELECT * FROM address ";
    private String SELECT_ALL_CITIES = "SELECT * FROM cities ";
    private String SELECT_ALL_STREETS = "SELECT street_id,street_name FROM streets ";
    private String ADD_NEW_ADDRESS = "INSERT INTO addresses (city_id, street_id, building_No) VALUES (?,?,?)";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    public Address(Connection con) {
        this.connection = con;
    }

    public void getAllAddress() throws SQLException {
        int index = 1;
        System.out.println("Current exist address are: ");
        preparedStatement = connection.prepareStatement(SELECT_ALL_ADDRESS);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int address_id = rs.getInt("address_id");
            int city_id = rs.getInt("city_id");
            int street_id = rs.getInt("street_id");
            int building_No = rs.getInt("building_No");

            System.out.print(index++ + ". Address id : " + address_id);
            System.out.println("     | City id : " + city_id);
            System.out.println("     | Street id : " + street_id);;
            System.out.println("     | Building number : " + building_No);
        }
        System.out.println();
    }

    public void getAllCities() throws SQLException {
        int index = 1;
        System.out.println("Current Cities are: ");
        preparedStatement = connection.prepareStatement(SELECT_ALL_CITIES);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int city_id = rs.getInt("city_id");
            String city_name = rs.getString("city_name");
            System.out.print(index++ + ". City id : " + city_id);
            System.out.println("     | City name : " + city_name);
        }
        System.out.println();
    }

    public void getAllStreets() throws SQLException {
        int index = 1;
        System.out.println("Current streets are: ");
        preparedStatement = connection.prepareStatement(SELECT_ALL_STREETS);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int street_id = rs.getInt("street_id");
            String street_name = rs.getString("street_name");
            System.out.print(index++ + ". streets id : " + street_id);
            System.out.println("     | streets name : " + street_name);
        }
        System.out.println();
    }

    public void setNewAddress(int city_id, int street_id, int building_no) throws SQLException {
        preparedStatement = connection.prepareStatement(ADD_NEW_ADDRESS);
        preparedStatement.setInt(1,city_id);
        preparedStatement.setInt(2,street_id);
        preparedStatement.setInt(3, building_no);
        preparedStatement.executeUpdate();
    }
}

