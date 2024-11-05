package app.dto;

public class UserDto {
    private long id;
    private String userName;
    private String password;
    private String role;
    private PersonDto personId;

    public UserDto() {}

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

    public PersonDto getPersonId() {
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

    public void setPersonId(PersonDto personId) {
        this.personId = personId;
    }

}
