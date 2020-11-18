package api;

import core.User;
import domain.user.*;
import infrastructure.DBexception;

public class Api {

    private final UserFactory userFactory;
    private final UserRepository userRepository;
    private final UserServices userServices;

    public Api(UserFactory userFactory, UserRepository userRepository, UserServices userServices) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
        this.userServices = userServices;
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
    public synchronized User createUser(String user_email, String password1, String password2) throws UserExists, InvalidPassword, DBexception {
        //Generate salt
        byte[] salt = User.generateSalt();
        //Generate secret
        byte[] secret = User.calculateSecret(salt, password1);
        //Create user

        //Validate user input
        if(userServices.userAldreadyExistsInDB(user_email)){
            throw new UserExists();

        } else if (!password1.equals(password2)){
            throw new InvalidPassword();

        } else {
            //Create user
            User user = new User(user_email, "customer", salt, secret);
            //Save/create the user in the DB and return the users (No longer id -1)
            user = userFactory.createUser(user);
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
    public synchronized User login(String email, String password) throws InvalidPassword, UserNotFound, DBexception {

        //Get user from the DB with a specific name
        User user = userServices.login(email);

        //Username is null => no user was found in DB
        if(user.getUserEmail() == null){
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
