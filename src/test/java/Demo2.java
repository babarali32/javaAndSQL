import java.sql.*;

public class Demo2 {
    public static void main(String[] args) {
        String dBurl = "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12676917";
        String username = "sql12676917";
        String password = "UjMNKciRkP";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ResultSetMetaData resultSetMetaData=null;
        try {
            // Establishing the connection
            connection = DriverManager.getConnection(dBurl, username, password);
            System.out.println(connection);

            // Creating a statement
            statement = connection.createStatement();

            // Executing a SELECT query
            resultSet = statement.executeQuery("SELECT * FROM person");

            // Retrieving metadata about the structure of the ResultSet
            resultSetMetaData=resultSet.getMetaData();
            for (int i = 1; i <=resultSetMetaData.getColumnCount(); i++) {

                String columnName = resultSetMetaData.getColumnName(i);
                String columnType = resultSetMetaData.getColumnTypeName(i);
                int columnSize = resultSetMetaData.getColumnDisplaySize(i);
                 String  catalogName  = resultSetMetaData.getCatalogName(i);
                 String schemaName  = resultSetMetaData.getSchemaName(i);
                // You can print or use the column information as needed
               System.out.println( "Column Name: "+columnName);
                System.out.println("Column Type: " + columnType);
                System.out.println("Column Size: " + columnSize);
                System.out.println("Catalog Name: " + catalogName);
                System.out.println("Schema Name: " + schemaName);
            }
            System.out.println();
            System.out.println("--------------------------------------");
            // Process the result set (iterate through the rows if needed)
            while (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    String columnName = resultSetMetaData.getColumnName(i);
                     String rows =resultSet.getString(resultSetMetaData.getColumnName(i));
                     System.out.print(rows+" ");
                    // Process the columnName as needed
//                    // Retrieve data from the result set
//                    int personId = resultSet.getInt("id");
                    String name = resultSet.getString("firstname");
////                    // Process the data as needed
//                    //System.out.println("Person ID: " + personId + ", Name: " + name);
//                    System.out.println("----------------------------------");
//                    System.out.print(name+" ");
//                    System.out.println("------------------------------------");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        } finally {
            // Close resources in the reverse order of their creation
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
