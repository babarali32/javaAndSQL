import java.sql.*;

public class practiceClass {
    public static void main(String[] args) {
        String dBurl = "jdbc:mysql://localhost:3306/root";
        String username = "root";
        String password = "UjMNKciRkP";
        // Declaration of Connection object to establish a connection to the database
        Connection connection = null;
        // Declaration of Statement object to execute SQL queries against the database
        Statement statement = null;
        // Declaration of ResultSet object to store the result set obtained from a database query
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
