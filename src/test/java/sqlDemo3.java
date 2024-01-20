import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class sqlDemo3 {
    public static void main(String[] args) {
        String dBurl = "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12676917";
        String username = "sql12676917";
        String password = "UjMNKciRkP";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData resultSetMetaData=null;
        try {
            connection = DriverManager.getConnection(dBurl, username, password);
            System.out.println(connection);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM person");
            resultSetMetaData=resultSet.getMetaData();

            List<Map<String,String >> tableData=new ArrayList<>();
            for (int i = 1; i <=resultSetMetaData.getColumnCount(); i++) {
                String columnName = resultSetMetaData.getColumnName(i);
                int columnSize = resultSetMetaData.getColumnDisplaySize(i);
                System.out.println( "Column Name: "+columnName);
                System.out.println("Column Size: " + columnSize);
            }
            System.out.println();
            System.out.println("--------------------------------------");
            while (resultSet.next()) {
                Map<String,String> row=new LinkedHashMap<>();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    String columnName = resultSetMetaData.getColumnName(i);
                    System.out.println("Column Name: "+columnName+" ");
                    String rows =resultSet.getString(resultSetMetaData.getColumnName(i));
                   row.put(resultSetMetaData.getColumnName(i), resultSet.getString(resultSetMetaData.getColumnName(i)));
                }
                System.out.println();
               tableData.add(row);
            }
            System.out.println(tableData);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


