package app.model;

public class InvoiceDetail {
    private long id;
    private Invoice invoiceId;
    private int item;
    private String description;
    private double amount;

    public InvoiceDetail() {
    }

    public long getId() {
        return id;
    }

    public Invoice getInvoiceId() {
        return invoiceId;
    }

    public int getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setInvoiceId(Invoice invoiceId) {
        this.invoiceId = invoiceId;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}