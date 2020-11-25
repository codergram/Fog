package domain.user;

import infrastructure.DBException;

public interface UserFactory {

    User createUser(User user) throws DBException;


}
