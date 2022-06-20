import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;



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

            UploadDocuments(documents, st);

            // ResultSet rs = st.executeQuery("SELECT * FROM users");


        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database! -> " + database, e);
        }

    }

    

    /**
     * Generates a list of documents
     * @param quantity
     * @return ArrayList<Document>
     */
    public static ArrayList<Document> DocGenerator(int quantity) {

        ArrayList<Document> documents = new ArrayList<Document>();

        for (int i = 1; i < quantity + 1; i++) {

            Document doc = new Document(i, "Document " + i, new Date(i), "C:/documents/doc" + i, 1, 1);

            documents.add(doc);

        }

        //displayDocuments(documents);

        return documents;

    }
    

    /**
     * Display a list of documents
     * @param documents
     */
    public static void displayDocuments(ArrayList<Document> documents) {

        for (Document doc : documents) {
            System.out.println(doc.toString());
        }

    }
    
    public static void UploadDocuments(ArrayList<Document> documents, Statement st) {
        
        for (Document doc : documents) {

            try {
                st.executeUpdate(

                        "INSERT INTO Document (DocumentID, DocumentName, DocumentDate, StorageAddress, TopicID, CategoryID) VALUES ("

                        + doc.getDocumentID() + ", '"
                        + doc.getDocumentName() + "', '"
                        + doc.getDocumentDate() + "', '"
                        + doc.getStorageAddress() + "', "
                        + doc.getTopicID() + ", "
                        + doc.getCategoryID() + ")"
                );

                System.out.println("\nDocument " + doc.getDocumentID() + " uploaded!\n");

            } catch (SQLException e) {
                System.out.println("Error uploading document " + doc.getDocumentID());
                System.out.println(e);
            }
        }
    }

}


