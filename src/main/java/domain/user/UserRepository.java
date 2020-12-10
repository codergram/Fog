/*
 * Copyright (c) 2020. Team CoderGram
 *
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 * @author Sigurd Arik Twena Nielsen (cph-at89@cphbusiness.dk)
 * @author Jacob Lange Nielsen (cph-jn352@cphbusiness.dk)
 */

package domain.user;

import domain.user.exceptions.UserExists;
import domain.user.exceptions.UserNotFound;
import java.util.List;

public interface UserRepository extends UserFactory {

  List<User> getAllUsersFromDB() throws UserNotFound;

  User getUserById(int userId) throws UserNotFound, UserExists;

  User getUserByEmail(String email) throws UserNotFound, UserExists;

  void updateUserById(int userId, User user) throws UserNotFound;

  void deleteUserById(int userId) throws UserNotFound;

  void changeUserRole(int userId, User.Role role) throws UserNotFound;
}
