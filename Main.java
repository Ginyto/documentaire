import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

        String database = "docu";
        String url = "jdbc:mysql://localhost:3306/" + database;
        String username = "root";
        String password = "gianlucca";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            System.out.println("\nDatabase connected! to " + database + "\n");

            // Statement st = connection.createStatement();

            // ResultSet rs = st.executeQuery("SELECT * FROM users");

            





        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database! -> " + database, e);
        }

    }



}


