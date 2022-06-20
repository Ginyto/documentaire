public class Tag {

    private int Id_Tag;
    private String TagName;

    public Tag(int Id_Tag, String TagName) {
        this.Id_Tag = Id_Tag;
        this.TagName = TagName;
    }

    public int getId_Tag() {
        return Id_Tag;
    }

    public void setId_Tag(int Id_Tag) {
        this.Id_Tag = Id_Tag;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String TagName) {
        this.TagName = TagName;
    }

    @Override
    public String toString() {
        return "Tag{" + "Id_Tag=" + Id_Tag + ", TagName=" + TagName + '}';
    }
}
