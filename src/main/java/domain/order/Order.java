package domain.order;

import domain.carport.Carport;
import domain.customer.Customer;
import domain.user.User;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    
    public enum Status{
        New,
        Awaiting,
        Accepted,
        Paid,
        Completed,
        Cancelled
    }

    private int id;
    private final double width;
    private final double length;
    private final Timestamp timestamp;
    private final User salesEmployee;
    private final Customer customer;
    private final Enum<Status> status;
    private final Carport carport;
    private double margin;
    
    
    public Order(int id, double width, double length, Timestamp timestamp, User salesEmployee, Customer customer, Enum<Status> status, Carport carport, double margin) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.timestamp = timestamp;
        this.salesEmployee = salesEmployee;
        this.customer = customer;
        this.status = status;
        this.carport = carport;
        this.margin = margin;
    }

    public Order(double width, double length, Customer customer, Carport carport) {
        this.id = -1;
        this.width = width;
        this.length = length;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.salesEmployee = null;
        this.customer = customer;
        this.status = Order.Status.New;
        this.carport = carport;
        this.margin = 30.0;
    }
    
    public int getId() {
        return id;
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getLength() {
        return length;
    }
    
    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    public User getSalesEmployee() {
        return salesEmployee;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public Enum<Status> getStatus() {
        return status;
    }
    
    public Carport getCarport() {
        return carport;
    }
    
    public double getMargin() {
        return margin;
    }
    
    public void setMargin(double margin) {
        this.margin = margin;
    }
    
    public boolean hasSalesman(){
        return salesEmployee != null;
    }
    
    public String getOrderDate(){
        Date date = new Date(this.timestamp.getTime());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(date);
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", width=" + width +
                ", length=" + length +
                ", timestamp=" + timestamp +
                ", salesEmployee=" + salesEmployee +
                ", customer=" + customer +
                ", status=" + status +
                ", carport=" + carport +
                '}';
    }
}
