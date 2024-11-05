package app.dto;

import java.util.Date;

public class InvoiceDto {
    private long id;
    private PersonDto personId;
    private PartnerDto partnerId;
    private Date creationDate;
    private double amount;
    private boolean status;

    public InvoiceDto() {}

    public long getId() {
        return id;
    }

    public PersonDto getPersonId() {
        return personId;
    }

    public PartnerDto getPartnerId() {
        return partnerId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isStatus(String sin_pagar) {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPersonId(PersonDto personId) {
        this.personId = personId;
    }

    public void setPartnerId(PartnerDto partnerId) {
        this.partnerId = partnerId;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    
    
}
