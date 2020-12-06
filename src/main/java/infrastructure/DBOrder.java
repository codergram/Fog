package infrastructure;

import api.Api;
import api.Utils;
import domain.carport.Carport;
import domain.carport.shed.Shed;
import domain.customer.Customer;
import domain.material.materials.Material;
import domain.material.materials.Options;
import domain.material.materials.Tree;
import domain.order.Order;
import domain.order.OrderRepository;
import domain.order.exceptions.OrderException;
import domain.order.exceptions.OrderNotFound;
import domain.partslist.Part;
import domain.partslist.Partslist;
import domain.user.User;
import org.slf4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class DBOrder implements OrderRepository {
    private final Database database;
    private static final Logger log = getLogger(DBOrder.class);
    
    public DBOrder(Database database) {
        this.database = database;
    }
    
    
    @Override
    public List<Order> getALlOrders() throws OrderNotFound {
        //TODO: MySQL implementation
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1,400,500,null,
                new User(1,"Emil", "emil@emil.dk", User.Role.Admin),
                new Customer(1,"Kurt Verner", "Vejnavn 1", 1234, "Bynavn", 12345678, "kurt@verner.dk"),
                Order.Status.New,
                null));
        return orders;
    }
    
    @Override
    public Order getOrderById(int id) throws OrderNotFound {
        return null;
    }
    
    @Override
    public boolean deleteOrderById(int id) throws OrderException {
        return false;
    }
    
    @Override
    public Order updateOrderStatusById(int id, Order.Status status) throws OrderException {
        return null;
    }
    
    private int getPartUsageId(Part part){
        try (Connection conn = database.getConnection()) {
            
            Material mat = part.getMaterial();
            String usage = mat.getUsage().name();
            int matId = mat.getId();
            int typeId = -1;
    
            try(PreparedStatement ps = conn.prepareStatement("SELECT id FROM `type` WHERE name=?")){
                if(mat instanceof Tree) {
                    ps.setString(1, ((Tree) mat).getType().name());
                } else {
                    ps.setString(1,((Options) mat).getType().name());
                }
                
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    typeId = rs.getInt(1);
                }
            }
            
        
            try(PreparedStatement ps = conn.prepareStatement("SELECT id FROM `usage` WHERE name=? AND material_id=? AND type_id=?")){
                ps.setString(1,usage);
                ps.setInt(2,matId);
                ps.setInt(3,typeId);
            
                
            
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return -1;
    }
    
    private List<Integer> createPartsInDb(Partslist partlist, int partlistId){
        List<Integer> partIdsInDb = new ArrayList<>();
            try (Connection conn = database.getConnection()) {
                String sql = "INSERT INTO parts (description, usage_id, amount, length, partlist_id) " +
                        "VALUE (?,?,?,?,?);";
                for(Part p: partlist.getPartList()) {
                    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
                        ps.setString(1, p.getDescription());
                        ps.setInt(2, getPartUsageId(p));
                        ps.setInt(3, p.getAmount());
                        if(p.getMaterial() instanceof Tree) {
                            ps.setDouble(4, ((Tree) p.getMaterial()).getLength());
                        } else {
                            ps.setDouble(4,0.0);
                        }
                        ps.setInt(5, partlistId);
        
                        ps.executeUpdate();
        
                        ResultSet rs = ps.getGeneratedKeys();
                        if (rs.next()) {
                            int id = rs.getInt(1);
                            partIdsInDb.add(id);
                        }
                    }
                }
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        return partIdsInDb;
    }
    
    private int createPartlistInDb(){
        try (Connection conn = database.getConnection()) {
            String sql = "INSERT INTO partlists (id) VALUES (null);";
            
                try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                
                    ps.executeUpdate();
                
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return -1;
    }
    
    private int createShedInDb(Shed shed){
        try (Connection conn = database.getConnection()) {
            String sql = "INSERT INTO sheds (length, width) VALUES (?,?);";
            
            try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                
                ps.setDouble(1,shed.getLength());
                ps.setDouble(2,shed.getWidth());
                
                ps.executeUpdate();
                
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return -1;
    }
    
    @Override
    public Order createNewOrder(Order order) throws OrderException {
        double margin = Api.MARGIN;
        
        int partListId = createPartlistInDb();
        Customer customer = order.getCustomer();
        Carport carport = order.getCarport();
        int carportId = -1;
        
        int shedId = -1;
        
        createPartsInDb(carport.getPartslist(), partListId);
        
        String carportSql = "INSERT INTO carports(length, width, roof, price, partlist_id) " +
                "VALUE (?,?,?,?,?);";
        
        if(carport.hasShed()) {
            shedId = createShedInDb(carport.getShed());
            carportSql = "INSERT INTO carports(length, width, roof, shed_id, price, partlist_id) " +
                    "VALUE (?,?,?,?,?,?);";
        }
    
        try (Connection conn = database.getConnection()) {
    
            try(PreparedStatement ps = conn.prepareStatement(carportSql,
                    Statement.RETURN_GENERATED_KEYS)){
        
                if(carport.hasShed()) {
                    ps.setDouble(1, carport.getWidth());
                    ps.setDouble(2, carport.getLength());
                    ps.setString(3, carport.getRoofType().name());
                    ps.setInt(4, shedId);
                    ps.setDouble(5, order.getCarport().getPrice());
                    ps.setInt(6, partListId);
                } else {
                    ps.setDouble(1, carport.getWidth());
                    ps.setDouble(2, carport.getLength());
                    ps.setString(3, carport.getRoofType().name());
                    ps.setDouble(4, order.getCarport().getPrice());
                    ps.setInt(5, partListId);
                }
        
                ps.executeUpdate();
        
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    carportId = rs.getInt(1);
                }
            }
        
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO orders(width, length, customer_id, status, carport_id, margin) " +
                    "VALUE (?,?,?,?,?,?);",
                    Statement.RETURN_GENERATED_KEYS)){
                
                ps.setDouble(1, carport.getWidth());
                ps.setDouble(2,carport.getLength());
                ps.setInt(3,customer.getId());
                ps.setString(4, Order.Status.New.name());
                ps.setInt(5,carportId);
                ps.setDouble(6,margin);
            
                ps.executeUpdate();
            
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    order.setId(rs.getInt(1));
                    return order;
                }
            }} catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
