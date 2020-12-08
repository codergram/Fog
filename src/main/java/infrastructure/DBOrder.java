package infrastructure;

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
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

public class DBOrder implements OrderRepository {
    private final Database database;
    private static final Logger log = getLogger(DBOrder.class);
    
    public DBOrder(Database database) {
        this.database = database;
    }
    
    @Override
    public int getOrderNumberFromUUID(UUID uuid){
        try (Connection conn = database.getConnection()) {
        
            try(PreparedStatement ps = conn.prepareStatement("SELECT order_id FROM links WHERE uuid=?")){
                ps.setString(1, uuid.toString());
            
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
    
    @Override
    public List<Order> getAllOrders() throws OrderNotFound {
        List<Order> orders = new LinkedList<>();
        
        try (Connection conn = database.getConnection()) {
            
            String getOrdersQuery = "SELECT\n" +
                    "orders.id AS \"orderId\",\n" +
                    "orders.`timestamp`,\n" +
                    "orders.`status` AS \"orderStatus\",\n" +
                    "orders.length AS \"orderLength\",\n" +
                    "orders.width AS \"orderWidth\",\n" +
                    "orders.margin AS \"orderMargin\",\n" +
                    "users.id AS \"employeeId\",\n" +
                    "users.`name` AS \"employeeName\",\n" +
                    "users.email AS \"employeeMail\",\n" +
                    "users.role AS \"employeeRole\",\n" +
                    "customers.id AS \"customerId\",\n" +
                    "customers.`name` AS \"customerName\",\n" +
                    "customers.address AS \"customerAddress\",\n" +
                    "customers.postal AS \"customerZip\",\n" +
                    "customers.city AS \"customerCity\",\n" +
                    "customers.phone AS \"customerPhone\",\n" +
                    "customers.email AS \"customerEmail\",\n" +
                    "carports.id AS \"carportId\",\n" +
                    "carports.length AS \"carportLength\",\n" +
                    "carports.width AS \"carportWidth\",\n" +
                    "carports.roof AS \"carportRoof\",\n" +
                    "carports.price AS \"carportPrice\",\n" +
                    "sheds.length AS \"shedLength\",\n" +
                    "sheds.width AS \"shedWidth\",\n" +
                    "carports.partlist_id AS \"partlistId\",\n" +
                    "links.uuid AS \"uuid\"\n" +
                    "FROM\n" +
                    "orders\n" +
                    "LEFT JOIN users ON users.id = orders.employee_id\n" +
                    "JOIN customers ON customers.id = orders.customer_id\n" +
                    "JOIN carports ON carports.id = orders.carport_id\n" +
                    "JOIN partlists ON carports.partlist_id = partlists.id\n" +
                    "LEFT JOIN links ON links.order_id = orders.id\n" +
                    "LEFT JOIN sheds ON sheds.id = carports.shed_id\n" +
                    "ORDER BY orders.id DESC";
            
            try (PreparedStatement s = conn.prepareStatement(getOrdersQuery)){
                ResultSet rs = s.executeQuery();
            
                while (rs.next()) {
                    Order tmpOrder = null;
                    User tmpUser = null;
                    Customer tmpCustomer = null;
                    Carport tmpCarport = null;
                    Shed tmpShed = null;
                    UUID uuid = null;
                    
                    //Order data
                    int orderId = rs.getInt("orderId");
                    Timestamp timestamp = rs.getTimestamp("timestamp");
                    double orderMargin = rs.getDouble("orderMargin");
                    double orderLength = rs.getDouble("orderLength");
                    double orderWidth = rs.getDouble("orderWidth");
                    Order.Status orderStatus = Order.Status.valueOf(rs.getString("orderStatus"));
                    int employeeId = rs.getInt("employeeId");
                    int customerId = rs.getInt("customerId");
                    int carportId = rs.getInt("carportId");
                    int partlistId = rs.getInt("partlistId");
    
                    
                    
                    //UUID for links
                    if(rs.getString("uuid") != null){
                        String uuidString = rs.getString("uuid");
                        uuid = UUID.fromString(uuidString);
                    }

    
                    //Employee data
                    if(employeeId != 0) {
                        String employeeName = rs.getString("employeeName");
                        String employeeMail = rs.getString("employeeMail");
                        Enum<User.Role> employeeRole = User.Role.valueOf(rs.getString("employeeRole"));
                        tmpUser = new User(employeeId, employeeName, employeeMail, employeeRole);
                    }
                    
                    //Customer data
                    String customerName = rs.getString("customerName");
                    String customerAddress = rs.getString("customerAddress");
                    int customerZip = rs.getInt("customerZip");
                    String customerCity = rs.getString("customerCity");
                    int customerPhone = rs.getInt("customerPhone");
                    String customerEmail = rs.getString("customerEmail");
                    
                    tmpCustomer = new Customer(customerId, customerName, customerAddress, customerZip, customerCity, customerPhone, customerEmail);
    
                    
                    //Carport data
                    double carportLength = rs.getDouble("carportLength");
                    double carportWidth = rs.getDouble("carportWidth");
                    Carport.Roof carportRoof = Carport.Roof.valueOf(rs.getString("carportRoof"));
                    double carportPrice = rs.getDouble("carportPrice");
                    
                        //Shed data
                        double shedLength = rs.getDouble("shedLength");
                        double shedWidth = rs.getDouble("shedWidth");
                        
                        if(shedLength != 0 && shedWidth != 0) {
                            tmpShed = new Shed(shedLength, shedWidth);
                        }
                        
                        List<Part> matList = getPartsOnOrder(partlistId);
                        
                        Partslist partslist = new Partslist(matList);
                        
                    tmpCarport = new Carport(carportId, carportLength, carportWidth, carportRoof, tmpShed, partslist, carportPrice);
                    
                    tmpOrder = new Order(orderId, orderWidth, orderLength, timestamp, tmpUser, tmpCustomer, orderStatus, tmpCarport, orderMargin, uuid);
                    
                    //Add order to list
                    orders.add(tmpOrder);
                }
                return orders;
            }
            } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return orders;
    }
    
    private List<Part> getPartsOnOrder(int partlistId) {
        List<Part> parts = new ArrayList<>();
    
        try (Connection conn = database.getConnection()) {
        
            String getOrdersQuery = "SELECT \n" +
                    "parts.id AS \"partId\", parts.description AS \"description\", parts.amount AS \"amount\", parts.length AS \"length\",\n" +
                    "`usage`.`name` AS \"usageName\",\n" +
                    "materiale.`name` AS \"materialName\", materiale.price AS \"materialPrice\", materiale.unit AS \"materialUnit\",\n" +
                    "type.`name` AS \"typeName\"\n" +
                    "FROM parts\n" +
                    "JOIN `usage` ON `usage`.id = parts.usage_id\n" +
                    "JOIN materiale ON materiale.id = `usage`.material_id\n" +
                    "JOIN type ON type.id = `usage`.type_id\n" +
                    "WHERE parts.partlist_id = ?";
        
            try (PreparedStatement s = conn.prepareStatement(getOrdersQuery)){
                s.setInt(1, partlistId);
                
                
                ResultSet rs = s.executeQuery();
                
                while (rs.next()) {
                    int partId = rs.getInt("partId");
                    int amount = rs.getInt("amount");
                    String description = rs.getString("description");
                    int length = rs.getInt("length");
                    Material.Usage materialUsage = Material.Usage.valueOf(rs.getString("usageName"));
                    String materialName = rs.getString("materialName");
                    double materialPrice = rs.getDouble("materialPrice");
                    String matUnit = rs.getString("materialUnit");
                    String typeName = rs.getString("typeName");
                    
                    Material.Unit materialUnit = Material.Unit.valueOf(matUnit);
    
                    Material tmpMaterial = null;
                    
                    boolean materialTypeFound = false;
                    
                    for(Tree.Type t: Tree.Type.values()){
                        if(t.name().equals(typeName)){
                            tmpMaterial = new Tree(materialName, length, materialPrice, materialUsage, t, materialUnit);
                            materialTypeFound = true;
                            break;
                        }
                    }
                    
                    if(!materialTypeFound){
                        tmpMaterial = new Options(materialName, materialPrice, materialUsage, Options.Type.valueOf(typeName), materialUnit);
                    }
                    
                    Part tmpPart = new Part(tmpMaterial, amount, description);
                    
                    //Add part to list
                    parts.add(tmpPart);
                }}
        
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return parts;
    }
    
    @Override
    public Order getOrderById(int id) throws OrderNotFound {
        for(Order o: getAllOrders()){
            if(o.getId() == id){
                return o;
            }
        }
        return null;
    }
    
    @Override
    public boolean deleteOrderById(int id) throws OrderException {
        return false;
    }
    
    @Override
    public boolean updateOrderStatusById(int id, Order.Status status) throws OrderException {
        try (Connection conn = database.getConnection()) {
            try(PreparedStatement ps = conn.prepareStatement("UPDATE orders SET status=? WHERE id=?;")){
                ps.setString(1, status.name());
                ps.setInt(2,id);
                ps.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            throw new OrderException(e.getMessage());
        }
    }
    
    @Override
    public void assignOrder(int ordrenummer, int userId) throws OrderNotFound {
        try (Connection conn = database.getConnection()) {
            try(PreparedStatement ps = conn.prepareStatement("UPDATE orders SET orders.employee_id=? WHERE orders.id=?;")){
            
                ps.setInt(1, userId);
                ps.setInt(2,ordrenummer);
                ps.executeUpdate();
                
            }} catch (Exception e) {
            throw new OrderNotFound(e.getMessage());
        }
    }
    
    @Override
    public void updateMargin(int orderId, double newMargin) throws OrderException {
        try (Connection conn = database.getConnection()) {
            try(PreparedStatement ps = conn.prepareStatement("UPDATE orders SET margin=? WHERE id=?;")){
            
                ps.setDouble(1, newMargin);
                ps.setInt(2,orderId);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            throw new OrderException(e.getMessage());
        }
    }
    
    @Override
    public void releaseOrder(int orderId) throws OrderNotFound {
        try (Connection conn = database.getConnection()) {
            try(PreparedStatement ps = conn.prepareStatement("UPDATE orders SET orders.employee_id=NULL WHERE orders.id=?;")){
            
                ps.setInt(1, orderId);
                ps.executeUpdate();
            
            }} catch (Exception e) {
            throw new OrderNotFound(e.getMessage());
        }
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
        double margin = order.getMargin();
    
        UUID uuid = UUID.randomUUID();
        
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
                    ps.setDouble(1, carport.getLength());
                    ps.setDouble(2, carport.getWidth());
                    ps.setString(3, carport.getRoofType().name());
                    ps.setInt(4, shedId);
                    ps.setDouble(5, order.getCarport().getPrice());
                    ps.setInt(6, partListId);
                } else {
                    ps.setDouble(1, carport.getLength());
                    ps.setDouble(2, carport.getWidth());
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
                }
            }
    
            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO links (order_id, uuid) VALUE (?,?);")){
        
                ps.setInt(1, order.getId());
                ps.setString(2,uuid.toString());
        
                ps.executeUpdate();
                
                if(ps.getUpdateCount() > 0){
                    order.setUuid(uuid);
                    return order;
                }
            }
        
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
