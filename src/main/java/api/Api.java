package api;

import api.exceptions.EmailNotSent;
import api.exceptions.PDFNotCreated;
import domain.order.Order;
import domain.user.User;
import domain.user.*;
import domain.user.exceptions.InvalidPassword;
import domain.user.exceptions.UserExists;
import domain.user.exceptions.UserNotFound;
import infrastructure.exceptions.DBException;

import java.io.File;

public class Api {
    
    public final static String genericSiteTitle = "Fog Trælast";
    

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final FileService fileService;
    
    public Api(UserRepository userRepository, EmailService emailService, FileService fileService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.fileService = fileService;
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

    
    public synchronized User createUser(String user_email, String password1, String password2) throws UserExists, InvalidPassword, DBException {
        //Generate salt
        byte[] salt = User.generateSalt();
        //Generate secret
        byte[] secret = User.calculateSecret(salt, password1);
        //Create user

        //Validate user input
        if (!password1.equals(password2)){
            throw new InvalidPassword();

        } else {
            //Create user
            User user = new User(0,user_email,User.Role.Employee, salt, secret);
            //Save/create the user in the DB and return the users (No longer id -1)
            user = userRepository.createUser(user);
            return user;
        }
    }

    
    public synchronized User login(String email, String password) throws InvalidPassword, UserNotFound, DBException {

        //Get user from the DB with a specific name
        User user = null;
        try {
            user = userRepository.getUserByEmail(email);
        } catch (UserExists userExists) {
            userExists.printStackTrace();
        }
    
        //Username is null => no user was found in DB
        if(user.getEmail() == null){
            throw new UserNotFound();
        }
        //Validate the DB password with the provided one
        else if (!user.isPasswordCorrect(password)) {
            throw new InvalidPassword();
        }
        else  {
            //Return user if password is validated
            return user;
        }
    }

}
