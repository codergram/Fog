package domain.user;

import core.User;
import infrastructure.DBexception;

import java.util.ArrayList;

public interface UserRepository {

    ArrayList<User> getAllUsersFromDB() throws DBexception;

    User getUserById(int user_id) throws DBexception;

}
