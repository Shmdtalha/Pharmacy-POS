package Model.Entity;

public class Order extends ItemContainer{
    private String customerDetails;
    private String timestamp;




    public String getCustomer() { return customerDetails; }
    public void setCustomer(String customerDetails) { this.customerDetails = customerDetails; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public void cancel() {

    }

    public void generateInvoice() {

    }
}