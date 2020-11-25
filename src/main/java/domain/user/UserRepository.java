package domain.user;

import infrastructure.DBException;

import java.util.List;

public interface UserRepository extends UserFactory, UserServices {

    List<User> getAllUsersFromDB() throws DBException;

    User getUserById(int userId) throws DBException;

}
