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
        String cmd = "insert into persondetails(firstName,lastName,city,phoneNumber,state,zip) " +
                "values('" + firstName + "','" + lastName + "','" + city + "','" + phoneNumber + "','" + state +
                "','" + zip + "')";
        if (this.recordManipulation(cmd)) {
            System.out.println("Record Has Been Saved Sucessfully");
        } else {
            System.out.println("Not Added Successfully.");
        }
    }

    public void updateData(String phoneNumber, int type, String value) {
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
        if (this.recordManipulation(cmd)) {
            System.out.println("Information Updated");
        } else {
            System.out.println("Information Not Updated");
        }
    }

    public void deleteData(String mobileNumber) {
        String cmd = "delete from persondetails where phoneNumber='" + mobileNumber + "'";
        if (this.recordManipulation(cmd)) {
            System.out.println("Person Deleted");
        } else {
            System.out.println("Person Not Deleted");
        }
    }

    public void sort() {
        try {
            connection = DBConnection.getConnection();
            statement = connection.createStatement();
            String query = "select * from persondetails ORDER BY firstName" + " ASC";
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}