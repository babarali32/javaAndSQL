import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlClass01 {
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
        {
            try {
                connection = DriverManager.getConnection(dbUrl, username, password);
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM person");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("id") + " " +
                            resultSet.getString("FirstName") + " " +
                            resultSet.getString("lastname")
                    );
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
}
