package api;

import api.exceptions.EmailNotSent;
import api.exceptions.PDFNotCreated;
import domain.carport.Carport;
import domain.customer.Customer;
import domain.customer.CustomerRepository;
import domain.material.MaterielRepository;
import domain.material.materials.Material;
import domain.order.Order;
import domain.order.exceptions.OrderException;
import domain.partslist.Part;
import domain.partslist.exceptions.PartslistServices;
import domain.svg.SVGFactory;
import domain.order.OrderRepository;
import domain.order.exceptions.OrderNotFound;
import domain.user.User;
import domain.user.*;
import domain.user.exceptions.InvalidPassword;
import domain.user.exceptions.UserExists;
import domain.user.exceptions.UserNotFound;
import infrastructure.LocalPartslist;
import infrastructure.exceptions.DBException;
import infrastructure.exceptions.PDFNotFound;
import org.slf4j.Logger;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

public class Api {
    
    public final static String genericSiteTitle = "Fog Trælast";
    public final static double requiredMargin = 15.0;
    private static final Logger log = getLogger(Api.class);

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final FileService fileService;
    private final SVGFactory svgFactory;
    private final MaterielRepository materielRepository;
    private final PartslistServices partslistServices;
    private final OrderRepository orderRepository;
    private final CustomerRepository customererRepository;
    
    public Api(UserRepository userRepository, EmailService emailService, FileService fileService, SVGFactory svgFactory,
               MaterielRepository materielRepository, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.fileService = fileService;
        this.svgFactory = svgFactory;
        this.materielRepository = materielRepository;
        this.orderRepository = orderRepository;
        this.partslistServices = new LocalPartslist(materielRepository);
        customererRepository = customerRepository;
    }
    
    public File createPdf(Order o) throws PDFNotCreated{
        return fileService.generatePdf(o, getSVGSide(o.getCarport(), false), getSVGTop(o.getCarport(), false));
    }
    
    public File getPdf(String filename) throws PDFNotFound {
        return fileService.getPdf(filename);
    }
    
    public synchronized boolean sendMail(String mailAddress, String title, String subject, String msg, File file){
        try {
            String message = Utils.fileToString("mail/mailtemplate.html")
                    .replace("$$TITEL$$", title)
                    .replace("$$OVERSKRIFT$$", subject)
                    .replace("$$TEKST$$", msg);
            
            emailService.sendEmail(mailAddress, title, message, file);
            return true;
        } catch (EmailNotSent e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public synchronized boolean sendMail(String mailAddress, String title, String subject, String msg){
        try {
            String message = Utils.fileToString("mail/mailtemplate.html")
                    .replace("$$TITEL$$", title)
                    .replace("$$OVERSKRIFT$$", subject)
                    .replace("$$TEKST$$", msg);
            
            emailService.sendEmail(mailAddress, title, message, null);
            return true;
        } catch (EmailNotSent e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public synchronized boolean sendLinkByMail(Order order, String link){
        try {
            String message = Utils.fileToString("mail/mailtemplate.html")
                    .replace("$$TITEL$$", "Ordre " + order.getId())
                    .replace("$$OVERSKRIFT$$", "Dit nye link til ordren")
                    .replace("$$TEKST$$", link);
            
            emailService.sendEmail(order.getCustomer().getEmail(), "Ordre " + order.getId(), message, null);
            return true;
        } catch (EmailNotSent e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    
    public synchronized User createUser(String name, String email, String password, User.Role role) throws UserExists {
        var salt = User.generateSalt();
        
            //Create user
            User user = new User(0, name, email,role, salt, User.calculateSecret(salt, password));
            //Save/create the user in the DB and return the users (No longer id -1)
            user = userRepository.createUser(user);
            return user;
    }
    
    public synchronized void deleteUser(int id) throws UserNotFound {
        userRepository.deleteUserById(id);
    }

    
    public synchronized User login(String email, String password) throws InvalidPassword, UserNotFound, DBException {
        User user = null;
        try {
            user = userRepository.getUserByEmail(email);
        } catch (UserExists userExists) {
            log.info(userExists.getMessage());
        }
    
        if(user == null){
            throw new UserNotFound();
        } else if (!user.isPasswordCorrect(password)) {
            throw new InvalidPassword();
        } else  {
            //Return user if password is validated
            return user;
        }
    }
    
    public synchronized List<User> getUsers(){
        try {
            return userRepository.getAllUsersFromDB();
        } catch (UserNotFound e){
            log.info(e.getMessage());
        }
        return null;
    }
    
    public synchronized Customer createCustomer(Customer customer) throws DBException {
        return customererRepository.createCustomer(customer);
    }
    
    public synchronized Order createOrder(Order order, Customer customer) throws OrderException, DBException {
        List<Customer> customerList = new ArrayList();
        Customer tmpCustomer = customer;
        try {
             customerList = customererRepository.getAllCustomers();
        } catch (DBException e) {
            log.error(e.getMessage());
        }
        
        boolean found = false;
        for(Customer c: customerList){
            if(c.getName().equals(customer.getName()) || c.getEmail().equals(customer.getEmail())){
                tmpCustomer = c;
                found = true;
                break;
            }
        }
        
        if(!found){
            tmpCustomer = createCustomer(customer);
        }
        
        Order tmpOrder = new Order(order.getWidth(), order.getLength(), tmpCustomer, order.getCarport());
        
        
    
        return orderRepository.createNewOrder(tmpOrder);
    }
    
    public synchronized List<Order> getOrders() throws OrderNotFound {
        return orderRepository.getAllOrders();
    }

    public synchronized String getSVGSide(Carport carport, boolean isCustomer){
        return svgFactory.createSVGSideCarport(carport, isCustomer).getSvgSide();
    }

    public synchronized String getSVGTop(Carport carport, boolean isCustomer){
        return svgFactory.createSVGTopCarport(carport, isCustomer).getSvgTop();
    }

    public synchronized List<Material> getAllMaterielsFromDB() throws DBException {
        return materielRepository.getAllMaterials();
    }
    
    public List<Material> getAllRawMaterielsFromDB() throws DBException {
        return materielRepository.getAllRawMaterials();
    }

    public synchronized List<Part> getLocalPartslist(){
        return partslistServices.createPartsList();
    }

    public synchronized List<Part> addToLocalPartslist(Carport carport, List<Material> allMaterialsFromDB, List<Part> LocalPartlist){
        return partslistServices.addToPartslist(carport, allMaterialsFromDB, LocalPartlist);
    }
    
    public synchronized void assignOrder(int ordrenummer, int userId) throws OrderNotFound {
        orderRepository.assignOrder(ordrenummer, userId);
    }
    
    public synchronized List<Customer> getCustomers() {
        try {
            return customererRepository.getAllCustomers();
        } catch (DBException e){
            log.info(e.getMessage());
        }
        return null;
    }
    
    public synchronized void changeOrderStatus(int orderId, String status) throws OrderException {
        orderRepository.updateOrderStatusById(orderId, Order.Status.valueOf(status));
    }
    
    public synchronized void updatePrice(int orderId, double newPrice) throws OrderNotFound, OrderException {
        Order tmpOrder = orderRepository.getOrderById(orderId);
        double oldPrice = tmpOrder.getCarport().getPrice();
        double newMargin = ((newPrice / oldPrice) - 1.0) * 100;
        if(newMargin >= requiredMargin){
            orderRepository.updateMargin(orderId, newMargin);
        } else {
            throw new OrderException("Dækningsgrad er for lav!");
        }
        
    }
    
    public synchronized void releaseOrder(int orderId) throws OrderNotFound {
        orderRepository.releaseOrder(orderId);
    }
    
    public synchronized int getOrderByUUID(String uuid){
        return orderRepository.getOrderNumberFromUUID(UUID.fromString(uuid));
    }
    
    public synchronized Order getOrderById(int id) throws OrderNotFound {
        return orderRepository.getOrderById(id);
    }
    
    public void updateMaterial(int materialId, String matName, double matPrice, Material.Unit matUnit) throws DBException {
        materielRepository.updateMaterial(materialId, matName, matPrice, matUnit);
    }
}
