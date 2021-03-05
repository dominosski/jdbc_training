package com.company;

import java.sql.*;

public class Driver {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC";
        String userName = "root";
        String password = "root";
        try {

            Connection myConn = DriverManager.getConnection(url, userName, password);

            PreparedStatement preparedStatement = myConn.prepareStatement("SELECT * FROM employees where salary > ? and department = ?");

            preparedStatement.setDouble(1,80000);
            preparedStatement.setString(2,"Legal");

            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            int columnsNumber = resultSetMetaData.getColumnCount();

            while (resultSet.next())
            {
                for (int i = 1; i <= columnsNumber; i++) {
                    if(i > 1)
                    {
                        System.out.print(", ");
                    }
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println();
            }

            //4. Process the result

        }catch(Exception x)
        {
            x.printStackTrace();
        }
    }
}
