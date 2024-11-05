package app.model;

public class Person {
    private long id;
    private long document;
    private String name;
    private long celphone;

    public Person() {
    }

    public long getId() {
        return id;
    }

    public long getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public long getCelphone() {
        return celphone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDocument(long document) {
        this.document = document;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCelphone(long celphone) {
        this.celphone = celphone;
    }


}
