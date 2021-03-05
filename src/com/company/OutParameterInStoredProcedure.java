package com.company;

import java.sql.*;
import java.sql.CallableStatement;

public class OutParameterInStoredProcedure {

    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/demo?serverTimezone=UTC";
        String userName = "root";
        String password = "root!";

        try {
            Connection myConnection = DriverManager.getConnection(url, userName, password);

            String theDepartment = "Engineering";

            CallableStatement myStatement = myConnection.prepareCall("{call greet_the_department(?)}");


            /*myStatement.registerOutParameter(1, Types.VARCHAR);
            myStatement.setString(1, theDepartment);

            System.out.println("Calling stored procedure. greet_the_department('" + theDepartment + "')");

            myStatement.execute();

            System.out.println("Finished calling stored procedure");

            String theResult = myStatement.getString(1);

            System.out.println("\nThe result = " + theResult);

            //////////////////////////////////////////////////////////////////

            CallableStatement myStatement2 = myConnection.prepareCall("{call get_count_for_department(?, ?)}");

            myStatement2.setString(1,theDepartment);
            myStatement2.registerOutParameter(2, Types.INTEGER);

            System.out.println("Calling stored procedure. get_count_for_department('" + theDepartment + "', ?)");

            myStatement2.execute();

            System.out.println("Finished calling stored procedure");

            int count = myStatement2.getInt(2);

            System.out.println("\nThe count = " + count);*/

            CallableStatement myStatement3 = myConnection.prepareCall("{call get_employees_for_department(?)}");

            myStatement3.setString(1, theDepartment);

            System.out.println("Calling stored procedure. get_employees_for_department('" + theDepartment + "')");

            myStatement3.execute();

            System.out.println("Finished calling stored procedure");

            ResultSet myRs = myStatement3.getResultSet();

            ResultSetMetaData resultSetMetaData = myRs.getMetaData();

            while (myRs.next())
            {
                for (int i = 1; i <= resultSetMetaData.getColumnCount() ; i++) {
                    if(i > 1 )
                    {
                        System.out.print(", ");
                    }
                    String resultValue = myRs.getString(i);
                    System.out.print(resultValue);
                }
                System.out.println();
            }
        }
        catch (Exception x)
        {
            x.printStackTrace();
        }
    }


}
