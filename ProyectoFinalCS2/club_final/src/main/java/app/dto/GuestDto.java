package app.dto;

public class GuestDto {
    private long id;
    public UserDto userId;
    private PartnerDto partnerId;
    private String status;

    public GuestDto() {}

    public long getId() {
        return id;
    }

    public UserDto getUserId() {
        return userId;
    }

    public PartnerDto getPartnerId() {
        return partnerId;
    }

    public String getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(UserDto userId) {
        this.userId = userId;
    }

    public void setPartnerId(PartnerDto partnerId) {
        this.partnerId = partnerId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    }

   
    
    
