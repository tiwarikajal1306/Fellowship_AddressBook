package com.AddressBook.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/addressbook?autoReconnect=true&useSSL=false";
    private static final String uid = "root";
    private static final String password = "root";

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, uid, password);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
