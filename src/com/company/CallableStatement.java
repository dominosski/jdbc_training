package com.company;

import java.sql.*;


public class CallableStatement {
    public static void main(String[] args) throws Exception{
        String url = "jdbc:mysql://127.0.0.1:3306/demo?serverTimezone=UTC";
        String userName = "root";
        String password = "root";
        try {

            String theDepartment = "Engineering";
            int theIncreaseAmount = 10000;

            Connection myConn = DriverManager.getConnection(url, userName, password);

            PreparedStatement selectSalaries = myConn.prepareStatement("SELECT * from employees WHERE department = ?");

            selectSalaries.setString(1, "Engineering");

            ResultSet resultSet1 = selectSalaries.executeQuery();
            ResultSetMetaData resultSetMetaData1 = resultSet1.getMetaData();

            System.out.println("Salaries before increasing: ");

            while(resultSet1.next())
            {
                for (int i = 1; i <= resultSetMetaData1.getColumnCount() ; i++) {
                    if(i>1)
                    {
                        System.out.print(", ");
                    }
                    String columnValue = resultSet1.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println();
            }

            java.sql.CallableStatement callableStatement = myConn.prepareCall("{call increase_salaries_for_department(?, ?)}");

            callableStatement.setString(1, theDepartment);
            callableStatement.setDouble(2, theIncreaseAmount);
            callableStatement.execute();

            ResultSet resultSet = selectSalaries.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            System.out.println("Salaries after increasing: ");
            while(resultSet.next())
            {
                for (int i = 1; i <= resultSetMetaData.getColumnCount() ; i++) {
                    if(i>1)
                    {
                        System.out.print(", ");
                    }
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println();
            }


        }catch(Exception x)
        {
            x.printStackTrace();
        }
    }
}
