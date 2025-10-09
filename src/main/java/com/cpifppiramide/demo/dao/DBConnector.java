package com.cpifppiramide.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static Connection connection;

    private DBConnector(){}

    public static Connection getInstance() {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "mysql.cvooc68auzgf.us-east-1.rds.amazonaws.com/Twitter",
                        "admin", "cachorritos11");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
