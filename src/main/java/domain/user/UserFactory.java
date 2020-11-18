package domain.user;

import core.User;
import infrastructure.DBexception;

public interface UserFactory {

    User createUser(User user) throws DBexception;


}
