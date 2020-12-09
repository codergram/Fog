package domain.user;

import domain.user.exceptions.UserExists;

public interface UserFactory {
    
    User createUser(User user) throws UserExists;
    
    
}
