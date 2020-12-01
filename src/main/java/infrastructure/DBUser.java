package infrastructure;

import domain.user.User;
import domain.user.UserRepository;
import domain.user.exceptions.UserExists;
import domain.user.exceptions.UserNotFound;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUser implements UserRepository {
    private final Database database;
    
    public DBUser(Database database) {
        this.database = database;
    }
    
    
    @Override
    public List<User> getAllUsersFromDB() throws UserNotFound {
        List<User> allUsersFromDB = new ArrayList<>();
    
        try (Connection conn = database.getConnection()){
        
            String query = "SELECT * FROM Users";
        
            PreparedStatement ps = conn.prepareStatement( query );
        
            ResultSet rs = ps.executeQuery();
        
            while ( rs.next() ) {
                int userId = rs.getInt("Users.id");
                String usersName = rs.getString("Users.name");
                String userEmail = rs.getString( "Users.email" );
                String userRole = rs.getString( "Users.role" );
            
                User user = new User( userId, usersName, userEmail, User.Role.valueOf(userRole));
            
                allUsersFromDB.add(user);
            }
        } catch ( SQLException ex ) {
            throw new UserNotFound();
        }
    
        return allUsersFromDB;
    }
    
    @Override
    public User getUserById(int userId) throws UserNotFound, UserExists {
        User user = null;
    
        try (Connection conn = database.getConnection()){
        
            String query = "SELECT * FROM Users "
                    + "WHERE id = ?";
        
            PreparedStatement ps = conn.prepareStatement( query );
            ps.setInt( 1, userId );
            ResultSet rs = ps.executeQuery();
        
            if ( rs.next() ) {
                String usersName = rs.getString("Users.name");
                String userEmail = rs.getString( "Users.email" );
                String userRole = rs.getString( "Users.role" );
            
                user = new User( userId, usersName, userEmail, User.Role.valueOf(userRole));
            
                return user;
            } else {
                throw new UserNotFound();
            }
        } catch ( SQLException ex ) {
            throw new UserExists(ex.getMessage());
        }
    }
    
    @Override
    public User createUser(User user) throws UserExists {
        try (Connection conn = database.getConnection()){
        
            //Prepare a SQL statement from the DB connection
            String query = "INSERT INTO Users (name, email, role, salt, secret) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement( query, Statement.RETURN_GENERATED_KEYS );
        
            //Link variables to the SQL statement
            ps.setString(1,user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getRole().name());
            ps.setBytes(4, user.getSalt());
            ps.setBytes(5, user.getSecret());
        
            //Execute the SQL statement to update the DB
            ps.executeUpdate();
        
            //Optional: Get result from the SQL execution, that returns the executed keys (user_id, user_name etc..)
            ResultSet rs = ps.getGeneratedKeys();
        
            //Search if there is a result from the DB execution
            if (rs.next()) {
                //Create user from the user_id key that is returned form the DB execution
                return user.withId(rs.getInt(1));
            
            } else {
                //Return null, if no result is returned form the execution
                return null;
            }
        } catch ( SQLException ex ) {
            throw new UserExists(ex.getMessage());
        }
    }
    
    @Override
    public void updateUserById(int userId, User user) throws UserNotFound {
        try (Connection conn = database.getConnection()){
        
            //Prepare a SQL statement from the DB connection
            String query = "UPDATE Users SET role = ?"
                    + " WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement( query );
        
            //Link variables to the SQL statement
            ps.setString(1, user.getRole().name());
            ps.setInt(2, userId);
        
            //Execute the SQL statement to update the DB
            ps.executeUpdate();
        
        } catch ( SQLException ex ) {
            throw new UserNotFound();
        }
    }
    
    @Override
    public void deleteUserById(int userId) throws UserNotFound {
        try (Connection conn = database.getConnection()){
        
            //Prepare a SQL statement from the DB connection
            String query = "DELETE FROM Users WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement( query );
        
            //Link variables to the SQL statement
            ps.setInt(1, userId);
        
            //Execute the SQL statement to update the DB
            ps.executeUpdate();
        
        } catch ( SQLException ex ) {
            throw new UserNotFound();
        }
    }
    
    @Override
    public void changeUserRole(int userId, User.Role role) throws UserNotFound {
        try (Connection conn = database.getConnection()){
        
            //Prepare a SQL statement from the DB connection
            String query = "UPDATE Users SET role = ? "
                    + " WHERE id = ? ";
            PreparedStatement ps = conn.prepareStatement( query );
        
            //Link variables to the SQL statement
            ps.setString(1, role.name());
            ps.setInt(2, userId);
            
            
        
            //Execute the SQL statement to update the DB
            ps.executeUpdate();
        
        } catch ( SQLException ex ) {
            throw new UserNotFound();
        }
    }
    
    @Override
    public User getUserByEmail(String email) throws UserNotFound, UserExists {
        try (Connection conn = database.getConnection()){
        
            //Prepare a SQL statement from the DB connection
            String query = "SELECT * FROM Users WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement( query );
        
            //Link variables to the SQL statement
            ps.setString(1, email);
        
            //Execute the SQL query and save the result
            ResultSet rs = ps.executeQuery();
        
            //Search if there is a result from the DB execution
            if (rs.next()) {
                int userId = rs.getInt("Users.id");
                String usersName = rs.getString("Users.name");
                String userMail = rs.getString( "Users.email" );
                User.Role userRole = User.Role.valueOf(rs.getString("Users.role"));
                byte[] userSalt = rs.getBytes("Users.salt");
                byte[] userSecret = rs.getBytes("Users.secret");
                
                return new User(userId, usersName, userMail,userRole,userSalt,userSecret);
            
            } else {
                throw new UserNotFound();
            }
        } catch (SQLException e) {
            throw new UserExists(e.getMessage());
        }
    }
}
