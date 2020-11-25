package api;

import domain.user.User;
import domain.bestilling.BestillingRepository;
import domain.carport.CarportRepository;
import domain.customer.CustomerRepository;
import domain.materiel.MaterielRepository;
import domain.user.*;
import domain.user.exceptions.InvalidPassword;
import domain.user.exceptions.UserExists;
import domain.user.exceptions.UserNotFound;
import infrastructure.*;

public class Api {
    
    public final static String genericSiteTitle = "Fog Trælast";
    
    private final Database database;

    private final UserRepository userRepository;
    private final BestillingRepository bestillingRepository;
    private final CarportRepository carportRepository;
    private final CustomerRepository customerRepository;
    private final MaterielRepository materielRepository;
    
    public Api(Database database) {
        this.database = database;
        this.userRepository = new DBUser(database);
        this.bestillingRepository = new DBOrder(database);
        this.carportRepository = new DBCarport(database);
        this.customerRepository = new DBCustomer(database);
        this.materielRepository = new DBMaterial(database);
    }
    
    protected boolean sendMail(String mailAddress, String title, String subject, String msg){
        try {
            String message = Utils.fileToString("resetmail.html")
                    .replace("$$TITEL$$", title)
                    .replace("$$OVERSKRIFT$$", subject)
                    .replace("$$TEKST$$", msg);
            
            Utils.sendEmail(mailAddress, title, message);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Create a user from the registration page
     * @param user_email
     * @param password1 needs to be the same as password2
     * @param password2 needs to be the same as password1
     * @return User that is signing up
     * @throws UserExists User is already registered in the DB
     * @throws InvalidPassword Passwords do not match
     */
    public synchronized User createUser(String user_email, String password1, String password2) throws UserExists, InvalidPassword, DBException {
        //Generate salt
        byte[] salt = User.generateSalt();
        //Generate secret
        byte[] secret = User.calculateSecret(salt, password1);
        //Create user

        //Validate user input
        if(userRepository.userAldreadyExistsInDB(user_email)){
            throw new UserExists(user_email);

        } else if (!password1.equals(password2)){
            throw new InvalidPassword();

        } else {
            //Create user
            User user = new User(0,user_email,User.Role.Employee, salt, secret);
            //Save/create the user in the DB and return the users (No longer id -1)
            user = userRepository.createUser(user);
            return user;
        }
    }

    /**
     * Login process
     * @param email
     * @param password
     * @return The user that is logging in
     * @throws UserNotFound
     * @throws InvalidPassword
     */
    public synchronized User login(String email, String password) throws InvalidPassword, UserNotFound, DBException {

        //Get user from the DB with a specific name
        User user = userRepository.login(email);

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
