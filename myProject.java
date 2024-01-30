import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class myProject {

    // JDBC URL, username, and password of PostgreSQL server
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "mohit";
    private static final String PASSWORD = "mohit";

    public static void main(String[] args) {
        Connection connection = null;
        System.out.println("hii");
        try {
            // Register the PostgreSQL driver
            Class.forName("org.postgresql.Driver");

            // Establish a connection
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // Perform database operations
            performDatabaseOperations(connection);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("database not connected");
            e.printStackTrace();
        } finally {
            // Close the connection in the finally block to ensure it's always closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void performDatabaseOperations(Connection connection) throws SQLException {
        // Example: Query data from a table
        String query = "SELECT * FROM not_psych";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Process the result set
                int id = resultSet.getInt("index");
                String name = resultSet.getString("questions");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        }
    }
}
