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

  public File createPdf(Order o) throws PDFNotCreated {
    return fileService.generatePdf(
        o, getSVGSide(o.getCarport(), false), getSVGTop(o.getCarport(), false));
  }

  public File getPdf(String filename) throws PDFNotFound {
    return fileService.getPdf(filename);
  }

  private String generateMailMessage(String title, String subject, String message) {
    return Utils.fileToString("mail/mailtemplate.html")
        .replace("$$TITEL$$", title)
        .replace("$$OVERSKRIFT$$", subject)
        .replace("$$TEKST$$", message);
  }

  public void sendMail(String mailAddress, String title, String subject, String msg, File file) {
    try {
      emailService.sendEmail(mailAddress, title, generateMailMessage(title, subject, msg), file);
    } catch (EmailNotSent e) {
      System.out.println(e.getMessage());
    }
  }

  public boolean sendMail(String mailAddress, String title, String subject, String msg) {
    try {
      emailService.sendEmail(mailAddress, title, generateMailMessage(title, subject, msg), null);
      return true;
    } catch (EmailNotSent e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

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

  public User createUser(String name, String email, String password, User.Role role)
      throws UserExists {
    var salt = User.generateSalt();

    User user = new User(0, name, email, role, salt, User.calculateSecret(salt, password));
    user = userRepository.createUser(user);
    return user;
  }

  public void deleteUser(int id) throws UserNotFound {
    userRepository.deleteUserById(id);
  }

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

  public List<User> getUsers() {
    try {
      return userRepository.getAllUsersFromDB();
    } catch (UserNotFound e) {
      log.info(e.getMessage());
    }
    return new ArrayList<>();
  }

  public Customer createCustomer(Customer customer) throws DBException {
    return customererRepository.createCustomer(customer);
  }

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

  public List<Order> getOrders() throws OrderNotFound {
    return orderRepository.getAllOrders();
  }

  public String getSVGSide(Carport carport, boolean isCustomer) {
    return svgFactory.createSVGSideCarport(carport, isCustomer).getSvgCode();
  }

  public String getSVGTop(Carport carport, boolean isCustomer) {
    return svgFactory.createSVGTopCarport(carport, isCustomer).getSvgTop();
  }

  public List<Material> getAllMaterielsFromDB() throws DBException {
    return materielRepository.getAllMaterials();
  }

  public List<Material> getAllRawMaterielsFromDB() throws DBException {
    return materielRepository.getAllRawMaterials();
  }

  public List<Part> getLocalPartslist() {
    return partslistServices.createPartsList();
  }

  public List<Part> addToLocalPartslist(
      Carport carport, List<Material> allMaterialsFromDB, List<Part> localPartlist) {
    return partslistServices.addToPartslist(carport, allMaterialsFromDB, localPartlist);
  }

  public void assignOrder(int ordrenummer, int userId) throws OrderNotFound {
    orderRepository.assignOrder(ordrenummer, userId);
  }

  public List<Customer> getCustomers() {
    try {
      return customererRepository.getAllCustomers();
    } catch (DBException e) {
      log.info(e.getMessage());
    }
    return new ArrayList<>();
  }

  public void changeOrderStatus(int orderId, String status) throws OrderException {
    orderRepository.updateOrderStatusById(orderId, Order.Status.valueOf(status));
  }

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

  public void releaseOrder(int orderId) throws OrderNotFound {
    orderRepository.releaseOrder(orderId);
  }

  public int getOrderByUUID(String uuid) {
    return orderRepository.getOrderNumberFromUUID(UUID.fromString(uuid));
  }

  public Order getOrderById(int id) throws OrderNotFound {
    return orderRepository.getOrderById(id);
  }

  public void updateMaterial(int materialId, String matName, double matPrice, Material.Unit matUnit)
      throws DBException {
    materielRepository.updateMaterial(materialId, matName, matPrice, matUnit);
  }
}
