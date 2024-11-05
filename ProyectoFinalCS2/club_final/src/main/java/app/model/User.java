package app.model;

public class User {
    private long id;
    private String userName;
    private String password;
    private String role;
    private Person personId;

    public User() {}

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

}