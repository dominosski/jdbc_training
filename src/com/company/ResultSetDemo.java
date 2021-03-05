package com.company;

import java.sql.*;

public class ResultSetDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC";
        String userName = "root";
        String password = "root!";

        try {
            Connection connection = DriverManager.getConnection(url, userName, password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT id, last_name, first_name, salary from employees");

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            int columnCount = resultSetMetaData.getColumnCount();
            System.out.println("Column count = " + columnCount);
            System.out.println();

            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Column name: " + resultSetMetaData.getColumnName(i));
                System.out.println("Column type: " + resultSetMetaData.getColumnTypeName(i));
                System.out.println("Is nullable?: " + resultSetMetaData.isNullable(i));
                System.out.println("Is Auto Increment?: " + resultSetMetaData.isAutoIncrement(i) + "\n");
            }
        }
        catch (Exception x)
        {
            x.printStackTrace();
        }
    }
}
