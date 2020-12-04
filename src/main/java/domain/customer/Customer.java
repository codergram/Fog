package domain.customer;

public class Customer {
    
    private final int id;
    private final String name;
    private final String address;
    private final int postalCode;
    private final String city;
    private final int phoneNo;
    private final String email;
    
    public Customer(int id, String name, String address, int postalCode, String city, int phoneNo, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public Customer(String name, String address, int postalCode, String city, int phoneNo, String email) {
        this.id = -1;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.phoneNo = phoneNo;
        this.email = email;
    }
    
    public Customer(int id, Customer customer){
        this(id, customer.getName(), customer.getAddress(), customer.getPostalCode(), customer.getCity(), customer.getPhoneNo(), customer.getEmail());
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public int getPostalCode() {
        return postalCode;
    }
    
    public String getCity() {
        return city;
    }
    
    public int getPhoneNo() {
        return phoneNo;
    }
    
    public String getEmail() {
        return email;
    }
}
