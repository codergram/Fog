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

  public static final String genericSiteTitle = "Fog Trælast";
  /** Set the minimum margin allowed for sales */
  public static final double requiredMargin = 15.0;

  private static final Logger log = getLogger(Api.class);

  private final UserRepository userRepository;
  private final EmailService emailService;
  private final FileService fileService;
  private final SVGFactory svgFactory;
  private final MaterielRepository materielRepository;
  private final PartslistServices partslistServices;
  private final OrderRepository orderRepository;
  private final CustomerRepository customererRepository;

  public Api(
      UserRepository userRepository,
      EmailService emailService,
      FileService fileService,
      SVGFactory svgFactory,
      MaterielRepository materielRepository,
      OrderRepository orderRepository,
      CustomerRepository customerRepository) {
    this.userRepository = userRepository;
    this.emailService = emailService;
    this.fileService = fileService;
    this.svgFactory = svgFactory;
    this.materielRepository = materielRepository;
    this.orderRepository = orderRepository;
    this.partslistServices = new LocalPartslist(materielRepository);
    customererRepository = customerRepository;
  }

  /**
   * @param o Order object
   * @return Generated PDF in a File object.
   * @throws PDFNotCreated Error in generating PDF
   */
  public File createPdf(Order o) throws PDFNotCreated {
    return fileService.generatePdf(
        o, getSVGSide(o.getCarport(), false), getSVGTop(o.getCarport(), false));
  }

  /**
   * @param filename Name of PDF file. Without extension and path
   * @return PDF as File
   * @throws PDFNotFound PDF was not found
   */
  public File getPdf(String filename) throws PDFNotFound {
    return fileService.getPdf(filename);
  }

  /**
   * Generates a String with HTML for the mail to be sent.
   *
   * @param title Title of the mail
   * @param subject Subject of the mail message
   * @param message Mail content/message
   * @return String with HTML string of Message
   */
  private String generateMailMessage(String title, String subject, String message) {
    return Utils.fileToString("mail/mailtemplate.html")
        .replace("$$TITEL$$", title)
        .replace("$$OVERSKRIFT$$", subject)
        .replace("$$TEKST$$", message);
  }

  /**
   * @param mailAddress Mail of recipient
   * @param title Title of the mail
   * @param subject Subject of the mail
   * @param msg Mail message. Either plain-text or HTML accepted
   * @param file File to be attached
   */
  public void sendMail(String mailAddress, String title, String subject, String msg, File file) {
    try {
      emailService.sendEmail(mailAddress, title, generateMailMessage(title, subject, msg), file);
    } catch (EmailNotSent e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Sends a mail without attached file. File gets set to Null
   *
   * <p>Overloaded methode: {@link Api#sendMail(String,String,String,String,File)}
   */
  public void sendMail(String mailAddress, String title, String subject, String msg) {
    try {
      emailService.sendEmail(mailAddress, title, generateMailMessage(title, subject, msg), null);
    } catch (EmailNotSent e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * @param order Order to be mailed to
   * @param link Link to be mailed
   */
  public void sendLinkByMail(Order order, String link) {
    try {
      String message =
          generateMailMessage("Ordre " + order.getId(), "Dit nye link til ordren", link);

      emailService.sendEmail(
          order.getCustomer().getEmail(), "Ordre " + order.getId(), message, null);
    } catch (EmailNotSent e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * @param name Users name
   * @param email Users email
   * @param password Users password (plain-text)
   * @param role Users role
   * @see User.Role
   * @return Complete User object with ID and UUID set.
   * @throws UserExists If user already exists in database
   */
  public User createUser(String name, String email, String password, User.Role role)
      throws UserExists {
    var salt = User.generateSalt();

    User user = new User(0, name, email, role, salt, User.calculateSecret(salt, password));
    user = userRepository.createUser(user);
    return user;
  }

  /**
   * @param id ID of user in database
   * @throws UserNotFound If user is not found in database
   */
  public void deleteUser(int id) throws UserNotFound {
    userRepository.deleteUserById(id);
  }

  /**
   * @param email Email of user
   * @param password Password of user
   * @return User object if correct, otherwise nulled user object
   * @throws InvalidPassword If password does not match
   * @throws UserNotFound If user does not exist in database
   */
  public User login(String email, String password) throws InvalidPassword, UserNotFound {
    User user = null;
    try {
      user = userRepository.getUserByEmail(email);
    } catch (UserExists userExists) {
      log.info(userExists.getMessage());
    }
    if (user == null) {
      throw new UserNotFound();
    } else if (!user.isPasswordCorrect(password)) {
      throw new InvalidPassword();
    } else {
      // Return user if password is validated
      return user;
    }
  }

  /** @return A list of all users in database */
  public List<User> getUsers() {
    try {
      return userRepository.getAllUsersFromDB();
    } catch (UserNotFound e) {
      log.info(e.getMessage());
    }
    return new ArrayList<>();
  }

  /**
   * @param customer Customer object to be created in database
   * @return Customer object with ID set
   * @throws DBException If database error occures
   */
  public Customer createCustomer(Customer customer) throws DBException {
    return customererRepository.createCustomer(customer);
  }

  /**
   * @param order Order object to be added to database
   * @param customer Customer object to be added to database
   * @return Complete order object with ID and UUID set
   */
  public Order createOrder(Order order, Customer customer) throws OrderException, DBException {
    List<Customer> customerList;
    customerList = new ArrayList<>();
    Customer tmpCustomer = customer;
    try {
      customerList = customererRepository.getAllCustomers();
    } catch (DBException e) {
      log.error(e.getMessage());
    }

    boolean found = false;
    for (Customer c : customerList) {
      if (c.getName().equals(customer.getName()) || c.getEmail().equals(customer.getEmail())) {
        tmpCustomer = c;
        found = true;
        break;
      }
    }

    if (!found) {
      tmpCustomer = createCustomer(customer);
    }

    Order tmpOrder =
        new Order(order.getWidth(), order.getLength(), tmpCustomer, order.getCarport());

    return orderRepository.createNewOrder(tmpOrder);
  }

  /** @return All orders from database in a List */
  public List<Order> getOrders() throws OrderNotFound {
    return orderRepository.getAllOrders();
  }

  /**
   * @param carport Carport to be drawn
   * @param isCustomer Values: True - No dimensions on drawing False - Dimensions shown on drawing
   * @return String with complete HTML of SVG
   */
  public String getSVGSide(Carport carport, boolean isCustomer) {
    return svgFactory.createSVGSideCarport(carport, isCustomer).getSvgCode();
  }

  /** See {@link Api#getSVGSide(Carport, boolean)} */
  public String getSVGTop(Carport carport, boolean isCustomer) {
    return svgFactory.createSVGTopCarport(carport, isCustomer).getSvgTop();
  }

  /** @return A list of all materials including usage and types from database */
  public List<Material> getAllMaterielsFromDB() throws DBException {
    return materielRepository.getAllMaterials();
  }

  /** @return A list of all material names and prices from database. */
  public List<Material> getAllRawMaterielsFromDB() throws DBException {
    return materielRepository.getAllRawMaterials();
  }

  /** @return List of parts required to build carport */
  public List<Part> getLocalPartslist() {
    return partslistServices.createPartsList();
  }

  /**
   * @param carport Carport to be built
   * @param allMaterialsFromDB List of materials
   * @param localPartlist Partlist to be populated with materials
   * @return Partlist of required materials to built carport
   */
  public List<Part> addToLocalPartslist(
      Carport carport, List<Material> allMaterialsFromDB, List<Part> localPartlist) {
    return partslistServices.addToPartslist(carport, allMaterialsFromDB, localPartlist);
  }

  /** Assign the order to specified salesperson. */
  public void assignOrder(int orderId, int userId) throws OrderNotFound {
    orderRepository.assignOrder(orderId, userId);
  }

  /** @return List of all customers in database */
  public List<Customer> getCustomers() {
    try {
      return customererRepository.getAllCustomers();
    } catch (DBException e) {
      log.info(e.getMessage());
    }
    return new ArrayList<>();
  }

  /**
   * Changes the status of the order in database
   *
   * @see Order.Status
   */
  public void changeOrderStatus(int orderId, String status) throws OrderException {
    orderRepository.updateOrderStatusById(orderId, Order.Status.valueOf(status));
  }

  /**
   * Calculates the new order margin
   *
   * @param newPrice The requested price to be saved in database
   */
  public void updatePrice(int orderId, double newPrice) throws OrderNotFound, OrderException {
    Order tmpOrder = orderRepository.getOrderById(orderId);
    double oldPrice = tmpOrder.getCarport().getPrice();
    double newMargin = ((newPrice / oldPrice) - 1.0) * 100;
    if (newMargin >= requiredMargin) {
      orderRepository.updateMargin(orderId, newMargin);
    } else {
      throw new OrderException("Dækningsgrad er for lav!");
    }
  }

  /** Releases order for every salesperson to get it assigned. */
  public void releaseOrder(int orderId) throws OrderNotFound {
    orderRepository.releaseOrder(orderId);
  }

  /** @return Readable order ID */
  public int getOrderByUUID(String uuid) {
    return orderRepository.getOrderNumberFromUUID(UUID.fromString(uuid));
  }

  /** Find order in database by ID */
  public Order getOrderById(int id) throws OrderNotFound {
    return orderRepository.getOrderById(id);
  }

  /**
   * @param materialId ID of material to be changed
   * @param matName New or current name
   * @param matPrice New or current price
   * @param matUnit New or current unit
   * @see Material.Unit
   */
  public void updateMaterial(int materialId, String matName, double matPrice, Material.Unit matUnit)
      throws DBException {
    materielRepository.updateMaterial(materialId, matName, matPrice, matUnit);
  }
}
