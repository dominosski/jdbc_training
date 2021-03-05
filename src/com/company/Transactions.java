package com.company;

import java.sql.*;
import java.util.Scanner;

public class Transactions {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        String url = "jdbc:mysql://127.0.0.1:3306/demo?serverTimezone=UTC";
        String userName = "root";
        String password = "root!";
        try {
            connection = DriverManager.getConnection(url, userName, password);

            connection.setAutoCommit(false);

            System.out.println("Salaries BEFORE:\n");
            showSalaries(connection);

            statement = connection.createStatement();
            statement.executeUpdate("delete from employees where department = 'HR'");

            statement.executeUpdate("update employees set salary=305000 where department = 'Engineering'");

            System.out.println("\n>> Transaction steps are ready.\n");

            boolean ok = askUserIfOkToSave();

            if(ok)
            {
                connection.commit();
                System.out.println("\n>> Transaction COMMITED");
            }
            else
            {
                connection.rollback();
                System.out.println("\n>> Transaction ROLLED BACK");
            }

            System.out.println("Salaries AFTER:\n");

            showSalaries(connection);


        }
        catch (Exception x)
        {
            x.printStackTrace();
        }

    }

    private static boolean askUserIfOkToSave() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Is it okay to save?  yes/no: ");
        String input = scanner.nextLine();

        scanner.close();

        return input.equalsIgnoreCase("yes");
    }

    private  static void showSalaries(Connection connection)
    {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from employees where (department = 'Engineering' or department = 'HR')");


            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            int columnCount = resultSetMetaData.getColumnCount();



            while (resultSet.next())
            {
                for (int i = 1; i <=columnCount ; i++) {
                    if(i > 1)
                    {
                        System.out.print(", ");
                    }
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println();
            }
        }catch (Exception x)
        {
            x.printStackTrace();
        }

    }
}
