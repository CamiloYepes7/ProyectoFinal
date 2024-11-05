package app.model;

public class Guest {
    private long id;
    private User userId;
    private Partner partnerId;
    private String status;

    public Guest() {
    }

    public long getId() {
        return id;
    }

    public User getUserId() {
        return userId;
    }

    public Partner getPartnerId() {
        return partnerId;
    }

    public String getStatus(String inactiva) {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setPartnerId(Partner partnerId) {
        this.partnerId = partnerId;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    }

