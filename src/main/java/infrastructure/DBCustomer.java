package infrastructure;

import domain.customer.Customer;
import domain.customer.CustomerRepository;
import infrastructure.exceptions.DBException;
import org.slf4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class DBCustomer implements CustomerRepository {
    private final Database database;
    private static final Logger log = getLogger(DBCustomer.class);
    
    public DBCustomer(Database database) {
        this.database = database;
    }
    
    
    @Override
    public List<Customer> getAllCustomers() throws DBException {
        List<Customer> allCustomers = new ArrayList<>();
        
        try (Connection conn = database.getConnection()) {
            
            String query = "SELECT * FROM customers";
            
            ResultSet rs;
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                rs = ps.executeQuery();
                
                
                while (rs.next()) {
                    int id = rs.getInt("customers.id");
                    String name = rs.getString("customers.name");
                    String address = rs.getString("customers.address");
                    int postalCode = rs.getInt("customers.postal");
                    String city = rs.getString("customers.city");
                    int phoneno = rs.getInt("customers.phone");
                    String email = rs.getString("customers.email");
                    
                    Customer cust = new Customer(id, name, address, postalCode, city, phoneno, email);
                    
                    allCustomers.add(cust);
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        
        return allCustomers;
    }
    
    @Override
    public Customer createCustomer(Customer customer) throws DBException {
        try (Connection conn = database.getConnection()) {
            
            //Prepare a SQL statement from the DB connection
            String query = "INSERT INTO customers (name, address, postal, city, phone, email) VALUES (?, ?, ?, ?, ?, ?)";
            ResultSet rs;
            try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                
                //Link variables to the SQL statement
                ps.setString(1, customer.getName());
                ps.setString(2, customer.getAddress());
                ps.setInt(3, customer.getPostalCode());
                ps.setString(4, customer.getCity());
                ps.setInt(5, customer.getPhoneNo());
                ps.setString(6, customer.getEmail());
                
                //Execute the SQL statement to update the DB
                ps.executeUpdate();
                
                //Optional: Get result from the SQL execution, that returns the executed keys (user_id, user_name etc..)
                rs = ps.getGeneratedKeys();
    
                //Search if there is a result from the DB execution
                if (rs.next()) {
                    //Create user from the user_id key that is returned form the DB execution
                    return new Customer(rs.getInt(1), customer);
        
                } else {
                    //Return null, if no result is returned form the execution
                    return null;
                }
            }
            
        } catch (SQLException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }
}
