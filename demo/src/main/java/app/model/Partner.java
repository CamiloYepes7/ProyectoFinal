package app.model;
import java.util.Date;

public class Partner {
    private long id;
    private User userId;
    private double amount;
    private String type;
    private Date creationDate;

    public Partner() {
    }

    public long getId() {
        return id;
    }

    public User getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}