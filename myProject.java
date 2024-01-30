import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

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
            System.out.println("database not connected: "+ e.getMessage());
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
            // ask what do they want to do?
            Scanner scanner = new Scanner(System.in);
            int exit = 1;
            while(exit == 1) {
                System.out.println("type update/ add to perform the task. Hit enter to exit");
                String user_input = scanner.nextLine();
                switch (user_input) {
                    case "update":
                        System.out.println("Enter index you want to update");
                        int updateIndex = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Write the updated question");
                        String updatedQuestion = scanner.nextLine();
                        updateQuestion(connection, updateIndex, updatedQuestion);
                        break;
                    case "add":
                        break;
                    case "":
                        exit = 0;
                        break;
                    default:
                        System.out.println("invalid input");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private  static void updateQuestion(Connection connection, int index, String question) throws Exception {
        try{

            System.out.println(index);
            System.out.println(question);
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

    private  static void addQuestion(Connection connection, String question) throws Exception {
        try{
            System.out.println(question);
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }

}
