package domain.order;


import domain.carport.Carport;
import domain.customer.Customer;
import domain.user.User;

import java.sql.Date;
import java.sql.Timestamp;

public class Order {
    
    public enum Status{
        New,
        Awaiting,
        Accepted,
        Paid,
        Completed
    }

    private final int id;
    private final double width;
    private final double length;
    private final Timestamp timestamp;
    private final User salesEmployee;
    private final Customer customer;
    private final Enum<Status> status;
    private final Carport carport;
    
    
    public Order(int id, double width, double length, Timestamp timestamp, User salesEmployee, Customer customer, Enum<Status> status, Carport carport) {
        this.id = id;
        this.width = width;
        this.length = length;
        this.timestamp = timestamp;
        this.salesEmployee = salesEmployee;
        this.customer = customer;
        this.status = status;
        this.carport = carport;
    }

    public Order(double width, double length, Customer customer, Carport carport) {
        this.id = -1;
        this.width = width;
        this.length = length;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.salesEmployee = null;
        this.customer = customer;
        this.status = Status.valueOf("New");
        this.carport = carport;
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
}
