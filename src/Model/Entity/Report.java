package Model.Entity;

public abstract class Report {
    protected String contents;

    public abstract void display();

    public String getContents() { return contents; }
    public void setContents(String contents) { this.contents = contents; }
}