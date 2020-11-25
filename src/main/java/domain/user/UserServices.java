package domain.user;

import infrastructure.DBException;

public interface UserServices {

    void updateUserById(int userId, User user) throws DBException;

    void deleteUserById(int userId) throws DBException;

    void changeUserRole(int userId, User.Role role) throws DBException;

    boolean userAldreadyExistsInDB(String email) throws DBException;

    User login(String email) throws DBException;

}
