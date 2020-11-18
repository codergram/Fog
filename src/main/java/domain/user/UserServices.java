package domain.user;

import core.User;
import infrastructure.DBexception;

public interface UserServices {

    void updateUserById(int user_id, String user_role, double user_credit) throws DBexception;

    void deleteUserById(int user_id) throws DBexception;

    void changeUserRoleToCustomer(int user_id) throws DBexception;

    void changeUserRoleToAdmin(int user_id) throws DBexception;

    boolean userAldreadyExistsInDB(String email) throws DBexception;

    User login(String user_email) throws DBexception;

}
