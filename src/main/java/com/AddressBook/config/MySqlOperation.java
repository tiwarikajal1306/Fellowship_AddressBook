package com.AddressBook.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlOperation {
    Connection connection = null;
    Statement statement = null;

    public void selectRecords() {
        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from persondetails");
            while (result.next()) {
                System.out.println(result.getInt(1) +
                        " " + result.getString(2) +
                        " " + result.getString(3) +
                        " " + result.getString(4) +
                        " " + result.getString(5) +
                        " " + result.getString(6) +
                        " " + result.getString(7));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public boolean recordManipulation(String query) {
        boolean flag = false;
        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            flag = statement.executeUpdate(query) > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return flag;
    }

    public void insertData(String firstName, String lastName, String city, String phoneNumber, String state, String zip) {
        MySqlOperation mySqlOperation = new MySqlOperation();
        String cmd = "insert into persondetails(firstName,lastName,city,phoneNumber,state,zip) " +
                "values('" + firstName + "','" + lastName + "','" + city + "','" + phoneNumber + "','" + state +
                "','" + zip + "')";
        if (mySqlOperation.recordManipulation(cmd)) {
            System.out.println("Record Has Been Saved Sucessfully");
        } else {
            System.out.println("Not Added Successfully.");
        }
    }

    public void updateData(String phoneNumber, int type, String value) {
        MySqlOperation mySqlOperation = new MySqlOperation();
        String cmd = null;
        switch (type) {
            case 1:
                cmd = "update persondetails set phoneNumber='" + value + "' where phoneNumber='" + phoneNumber + "'";
                break;
            case 2:
                cmd = "update persondetails set state='" + value + "' where phoneNumber='" + phoneNumber + "'";
                break;
            case 3:
                cmd = "update persondetails set city='" + value + "' where phoneNumber='" + phoneNumber + "'";
                break;
            case 4:
                cmd = "update persondetails set zip='" + value + "' where phoneNumber='" + phoneNumber + "'";
                break;
        }
        if (mySqlOperation.recordManipulation(cmd)) {
            System.out.println("Information updated");
        } else {
            System.out.println("Information not updated");
        }
    }
}