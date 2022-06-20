import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;



public class Main {

    public static void main(String[] args) {

        String database = "docu";
        String url = "jdbc:mysql://localhost:3306/" + database;
        String username = "root";
        String password = "gianlucca";

        
        ArrayList <Document> documents = DocGenerator(5);


        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            System.out.println("\nDatabase connected! to " + database + "\n");

            Statement st = connection.createStatement();

            // ResultSet rs = st.executeQuery("SELECT * FROM users");

            String sqlAddDoc = "INSERT INTO document(`DocumentID`,`DocumentDate`,`DocumentName`,`StorageAddress`,`TopicID`,`CategoryID`) VALUES(1,'2022-06-20','docu','adaazd',1,1);";

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database! -> " + database, e);
        }

    }

    

    public static ArrayList<Document> DocGenerator(int quantity) {

        ArrayList<Document> documents = new ArrayList<Document>();

        for (int i = 1; i < quantity + 1; i++) {

            Document doc = new Document(i, "Document " + i, new Date(), "C:/documents/doc" + i, 1, 1);

            documents.add(doc);

        }
        
        for (Document doc : documents) {
            System.out.println(doc.toString());
        }

        return documents;

    }

}


