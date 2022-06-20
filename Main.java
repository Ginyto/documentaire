import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class Main {

    public static void main(String[] args) {

        String database = "docu";
        String url = "jdbc:mysql://localhost:3306/" + database;
        String username = "root";
        String password = "gianlucca";

        ArrayList<Document> documents = DocGenerator(0);

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            System.out.println("\nDatabase connected! to " + database + "\n");

            Statement st = connection.createStatement();

            UploadDocuments(documents, st);

            System.out.println("\nDocuments uploaded!\n");

            downloadSortedDocuments("TopicID", st);
            downloadSortedDocuments("CategoryID", st);

            //st.executeUpdate("INSERT INTO document(`DocumentName`,`DocumentDate`,`StorageAddress`,`TopicID`,`CategoryID`) VALUES('test','2022-06-20','test',1,1)");

            // ResultSet rs = st.executeQuery("SELECT * FROM users");

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database! -> " + database, e);
        }

    }

    

    public static void displayDownloadDocuments(ResultSet rs, String title) {

        System.out.println("\n" + title + "\n");
        
        try {
            while (rs.next()) {
                System.out.println(rs.getString("DocumentName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    }


    public static void downloadSortedDocuments(String filtre, Statement st) throws SQLException {

        for (int i = 1; i < 6; i++) {

            ResultSet rs = st.executeQuery("SELECT * FROM document WHERE + " + filtre + "=" + i);

            displayDownloadDocuments(rs, "Documents sorted by " + filtre + " N° " + i);
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

            Document doc = new Document("Document " + i);

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
    

    /**
     * Uploads a list of documents to the database
     * @param documents a list of documents
     * @param st the statement
     */
    public static void UploadDocuments(ArrayList<Document> documents, Statement st) {

        for (Document doc : documents) {

            try {
                st.executeUpdate(sqlAddDocument(doc));

                System.out.println("\n" + doc.getDocumentName() + " uploaded!\n");

            } catch (SQLException e) {
                System.out.println("Error uploading document " + doc.getDocumentID());
                System.out.println(e);
            }
        }
    }

    
    /**
     * Generates a SQL query to add a document to the database
     * @param doc the document
     * @return String
     */
    public static String sqlAddDocument(Document doc) {

        String sql = "INSERT INTO Document (DocumentName, DocumentDate, StorageAddress, TopicID, CategoryID) VALUES ("
                + "'"

                + doc.getDocumentName() + "', '"
                + doc.getDocumentDate() + "', '"
                + doc.getStorageAddress() + "', "
                + doc.getTopicID() + ", "
                + doc.getCategoryID() + ")";

        //System.out.println(sql);

        return sql;

    }
    

    /**
     * Generates a SQL query to add a document to the database
     * 
     * @param doc the document
     * @return String
     */
    public static String sqlAddDocumentTag(Document doc) {

        String sql = "INSERT INTO Document (DocumentName, DocumentDate, StorageAddress, TopicID, CategoryID) VALUES ("+ "'"
            + doc.getDocumentName() + "', '"
            + doc.getDocumentDate() + "', '"
            + doc.getStorageAddress() + "', "
            + doc.getTopicID() + ", "
            + doc.getCategoryID() + ")";

        // System.out.println(sql);

        return sql;

    }

}


