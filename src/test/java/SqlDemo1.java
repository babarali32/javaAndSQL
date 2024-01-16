import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlDemo1 {
    public static void main(String[] args) {
        String dBurl = "jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12676917";
        String username = "sql12676917";
        String password = "UjMNKciRkP";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establishing the connection
            connection = DriverManager.getConnection(dBurl, username, password);

            // Creating a statement
            statement = connection.createStatement();

            // Executing a SELECT query
            resultSet = statement.executeQuery("SELECT * FROM person");

            // Process the result set (iterate through the rows if needed)
            while (resultSet.next()) {
                // Retrieve data from the result set
                int personId = resultSet.getInt("id");
                String name = resultSet.getString("firstname");

                // Process the data as needed
                System.out.println("Person ID: " + personId + ", Name: " + name);
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
