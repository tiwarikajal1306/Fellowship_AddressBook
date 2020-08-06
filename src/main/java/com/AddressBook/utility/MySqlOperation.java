package com.AddressBook.utility;

import java.sql.*;

public class MySqlOperation {

    final String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/addressbook";
    String uid = "root";
    String password = "root";

    public void selectRecords() {
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, uid, password);
            Statement stat = con.createStatement();
            ResultSet result = stat.executeQuery("select * from persondetails");
            while (result.next()) {
                System.out.println(result.getInt(1)+
                        " "+result.getString(2)+
                        " "+result.getString(3)+
                        " "+result.getString(4)+
                        " "+result.getString(5)+
                        " "+result.getString(6)+
                        " "+result.getString(7));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean recordManipulation(String query) {
        boolean flag = false;
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, uid, password);
            Statement stat = con.createStatement();
            flag = stat.executeUpdate(query) > 0 ? true : false;
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    public void insertData(String firstName, String lastName,  String city, String phoneNumber, String state, String zip) {
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
}