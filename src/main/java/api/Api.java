package api;

import api.exceptions.EmailNotSent;
import api.exceptions.PDFNotCreated;
import domain.order.Order;
import domain.order.OrderRepository;
import domain.order.exceptions.OrderNotFound;
import domain.user.User;
import domain.user.*;
import domain.user.exceptions.InvalidPassword;
import domain.user.exceptions.UserExists;
import domain.user.exceptions.UserNotFound;
import infrastructure.exceptions.DBException;
import org.slf4j.Logger;

import java.io.File;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class Api {
    
    public final static String genericSiteTitle = "Fog Trælast";
    private static final Logger log = getLogger(Api.class);

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final FileService fileService;
    private final OrderRepository orderRepository;
    
    public Api(UserRepository userRepository, EmailService emailService, FileService fileService, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.fileService = fileService;
        this.orderRepository = orderRepository;
    }
    
    public File testPdf(String path) throws PDFNotCreated {
        Order order = new Order(1,200,300,null,null,null,null,null);
        return fileService.generatePdf(path, order);
    }
    
    public boolean sendMail(String mailAddress, String title, String subject, String msg, File file){
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
    
    public synchronized List<Order> getOrders() throws OrderNotFound {
        return orderRepository.getALlOrders();
    }

}
