import java.sql.*;
public class SqlClass02 {
    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://3.239.253.255:3306/syntaxhrm_mysql";
        String username = "syntax_hrm";
        String password = "syntaxhrm123";
        // Declaration of Connection object to establish a connection to the database
        Connection connection = null;
        // Declaration of Statement object to execute SQL queries against the database
        Statement statement = null;
        // Declaration of ResultSet object to store the result set obtained from a database query
        ResultSet resultSet = null;
        ResultSetMetaData metaData = null;
        try {
            connection = DriverManager.getConnection(dbUrl, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM person");
            metaData = resultSet.getMetaData();

            System.out.println("------------last------------------");

            int columns = metaData.getColumnCount();  // this will tell number of columns
            System.out.println("Numbers of columns in table: " + columns);
            System.out.println("---------------------");
            for (int i = 1; i <= columns; i++) {
                String colName = metaData.getColumnName(i);
                String shema = metaData.getSchemaName(i);
                int sizecolumn = metaData.getColumnDisplaySize(i);
                String tablename = metaData.getTableName(i);
                System.out.println("Column Name: " + colName);
                System.out.println("Schema: " + shema);
                System.out.println("Display Size: " + sizecolumn);
                System.out.println("Table Name: " + tablename);
                System.out.println();
            }

            while (resultSet.next()) {
                for (int i = 1; i <= columns; i++) {
                    String columnName = metaData.getColumnName(i);
                    String rowData = resultSet.getString(columnName);
                    System.out.println(columnName + ": " + rowData);
                }
                System.out.println();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
