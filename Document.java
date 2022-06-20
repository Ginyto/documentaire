import java.sql.Date;

public class Document {

    private int DocumentID;
    private String DocumentName;
    private Date DocumentDate;
    private String StorageAddress;
    private int TopicID;
    private int CategoryID;

    public Document(int DocumentID, String DocumentName, java.sql.Date date, String StorageAddress, int TopicID,
            int CategoryID) {
        this.DocumentID = DocumentID;
        this.DocumentName = DocumentName;
        this.DocumentDate = date;
        this.StorageAddress = StorageAddress;
        this.TopicID = TopicID;
        this.CategoryID = CategoryID;
    }
    

    public int getDocumentID() {
        return this.DocumentID;
    }

    public void setDocumentID(int DocumentID) {
        this.DocumentID = DocumentID;
    }

    public String getDocumentName() {
        return this.DocumentName;
    }

    public void setDocumentName(String DocumentName) {
        this.DocumentName = DocumentName;
    }

    public Date getDocumentDate() {
        return this.DocumentDate;
    }

    public void setDocumentDate(Date DocumentDate) {
        this.DocumentDate = DocumentDate;
    }

    public String getStorageAddress() {
        return this.StorageAddress;
    }

    public void setStorageAddress(String StorageAddress) {
        this.StorageAddress = StorageAddress;
    }

    public int getTopicID() {
        return this.TopicID;
    }

    public void setTopicID(int TopicID) {
        this.TopicID = TopicID;
    }

    public int getCategoryID() {
        return this.CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    @Override
    public String toString() {
        return "\nDocument {\n\n" + "DocumentID = " + DocumentID + ",\nDocumentName = " + DocumentName + ",\nDocumentDate = "
                + DocumentDate + ",\nStorageAddress = " + StorageAddress + ",\nTopicID = " + TopicID + ",\nCategoryID = "
                + CategoryID + "\n\n}";
    }

    public void display() {
        System.out.println(this.toString());
    }


}
