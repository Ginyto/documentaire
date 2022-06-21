import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Random;



public class Main {

    public static void main(String[] args) {

        String database = "docu";
        String url = "jdbc:mysql://localhost:3306/" + database;
        String username = "root";
        String password = "gianlucca";

        ArrayList<Document> documents = DocGenerator(5);
        Random random = new Random();

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            System.out.println("\nDatabase connected! to " + database + "\n");

            Statement st = connection.createStatement();

            UploadDocuments(documents, st, random);

            System.out.println("\nDocuments uploaded!\n");

            downloadSortedDocuments("CategoryID", st);
            downloadSortedDocuments("TopicID", st);
            displayMostTopic(st);
            displayMostTag(st);
            fixDateDocument(st);
            getSizeOfAllBook(st);
            AddTagDocument(st, getSizeOfAllBook(st), random);

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database! -> " + database, e);
        }

    }

    public static int getSizeOfAllBook(Statement st) {

        int size = 0;

        try {

            ResultSet rs = st.executeQuery("SELECT * FROM Document");

            while (rs.next()) {
                size++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //System.out.println("\nSize of all books: " + size + "\n");

        return size;

    }
    
    public static void AddTagDocument(Statement st, int documentID, Random random) {

        int max = (1 + random.nextInt(3));

        for (int i = 0; i < max; i++) {

            int tagID = (1 + random.nextInt(5));

            try {

                st.executeUpdate("INSERT INTO detenir (DocumentID, Id_Tag) VALUES (" + documentID + ", " + tagID + ")");

            } catch (SQLException e) {
                
            }

        }

    }
    

    public static void fixDateDocument(Statement st) throws SQLException {
        st.executeUpdate("UPDATE document SET DocumentDate = '" + new Date(System.currentTimeMillis()) + "' WHERE DocumentDate IS NULL");

        System.out.println("\nDate fixed!\n");
    }
    

    public static void displayMostTag(Statement st) throws SQLException {

        int count = 0;

        for (int i = 1; i < 6; i++) {
            ResultSet rs = st.executeQuery("SELECT * FROM detenir WHERE Id_Tag = " + i);

            while (rs.next()) {
                count++;
            }

            System.out.println("Tag N° " + i + " has " + count + " documents");

            count = 0;

        }
    }


    public static void displayMostTopic(Statement st) throws SQLException {

        int count = 0;
        int currentCount = 0;
        int topicID = 0;

        for (int i = 1; i < 6; i++) {

            ResultSet rs = st.executeQuery("SELECT * FROM document WHERE TopicID = " + i);

            while (rs.next()) {

                currentCount++;

            }

            if (currentCount > count) {

                count = currentCount;
                topicID = i;
            }

            currentCount = 0;
        }
        
        System.out.println("\n\nTopic N° " + topicID + " is the best topic with " + count + " documents\n");

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
    public static void UploadDocuments(ArrayList<Document> documents, Statement st, Random random) {

        int id = getSizeOfAllBook(st) + 1;

        for (Document doc : documents) {

            try {
                st.executeUpdate(sqlAddDocument(doc));
                AddTagDocument(st, id, random);

                System.out.println("\n" + doc.getDocumentName() + " uploaded!\n");

                id++;

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
    public static String sqlAddDocumentTag(int documentID, int tagID) {

        String sql = "INSERT INTO detenir(`DocumentID`,`Id_Tag`) VALUES("

            + documentID + ", "
            + tagID +
            
        ")";
        

        // System.out.println(sql);

        return sql;

    }

}


