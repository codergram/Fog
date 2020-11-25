package infrastructure;

import domain.user.User;
import domain.user.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUser implements UserRepository {
    private final Database database;
    
    public DBUser(Database database) {
        this.database = database;
    }
    
    
    @Override
    public List<User> getAllUsersFromDB() throws DBException {
        List<User> allUsersFromDB = new ArrayList<>();
    
        try (Connection conn = database.getConnection()){
        
            String SQL = "SELECT user_id, user_email, user_role FROM user "
                    + "WHERE user_active = 1";
        
            PreparedStatement ps = conn.prepareStatement( SQL );
        
            ResultSet rs = ps.executeQuery();
        
            while ( rs.next() ) {
                int user_id = rs.getInt("user_id");
                String user_email = rs.getString( "user_email" );
                String user_role = rs.getString( "user_role" );
            
                User user = new User( user_id, user_email, User.Role.valueOf(user_role));
            
                allUsersFromDB.add(user);
            }
        } catch ( SQLException ex ) {
            throw new DBException();
        }
    
        return allUsersFromDB;
    }
    
    @Override
    public User getUserById(int userId) throws DBException {
        User user = null;
    
        try (Connection conn = database.getConnection()){
        
            String SQL = "SELECT user_email, user_role FROM user "
                    + "WHERE user_id = ?";
        
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, userId );
            ResultSet rs = ps.executeQuery();
        
            if ( rs.next() ) {
                String user_email = rs.getString( "user_email" );
                String user_role = rs.getString( "user_role" );
            
                user = new User( userId, user_email, User.Role.valueOf(user_role));
            
                return user;
            }
        } catch ( SQLException ex ) {
            throw new DBException();
        }
    
        return user;
    }
    
    @Override
    public User createUser(User user) throws DBException {
        try (Connection conn = database.getConnection()){
        
            //Prepare a SQL statement from the DB connection
            String SQL = "INSERT INTO user (user_email, user_role, salt, secret) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );
        
            //Link variables to the SQL statement
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getRole().name());
            ps.setBytes(3, user.getSalt());
            ps.setBytes(4, user.getSecret());
        
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
            throw new DBException();
        }
    }
    
    @Override
    public void updateUserById(int userId, User user) throws DBException {
        try (Connection conn = database.getConnection()){
        
            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE user SET user_role = ?"
                    + " WHERE user_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );
        
            //Link variables to the SQL statement
            ps.setString(1, user.getRole().name());
            ps.setInt(2, userId);
        
            //Execute the SQL statement to update the DB
            ps.executeUpdate();
        
        } catch ( SQLException ex ) {
            throw new DBException();
        }
    }
    
    @Override
    public void deleteUserById(int userId) throws DBException {
        try (Connection conn = database.getConnection()){
        
            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE user SET user_active = 0 "
                    + " WHERE user_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );
        
            //Link variables to the SQL statement
            ps.setInt(1, userId);
        
            //Execute the SQL statement to update the DB
            ps.executeUpdate();
        
        } catch ( SQLException ex ) {
            throw new DBException();
        }
    }
    
    @Override
    public void changeUserRole(int userId, User.Role role) throws DBException {
        try (Connection conn = database.getConnection()){
        
            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE user SET user_role = ? "
                    + " WHERE user_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );
        
            //Link variables to the SQL statement
            ps.setString(1, role.name());
            ps.setInt(2, userId);
        
            //Execute the SQL statement to update the DB
            ps.executeUpdate();
        
        } catch ( SQLException ex ) {
            throw new DBException();
        }
    }
    
    @Override
    public boolean userAldreadyExistsInDB(String email) throws DBException {
        try (Connection conn = database.getConnection()){
        
            //Prepare a SQL statement from the DB connection
            String SQL = "SELECT user_email FROM user WHERE user_email = (?)";
            PreparedStatement ps = conn.prepareStatement( SQL );
        
            //Link variables to the SQL statement
            ps.setString(1, email);
        
            //Execute the SQL query and save the result
            ResultSet rs = ps.executeQuery();
        
            //Search if there is a result from the DB execution
            if (rs.next()) {
                //Return true, if the is a DB execution result
                return true;
            
            } else {
                //Return false, if the isn't a DB execution result
                return false;
            }
        } catch (SQLException e) {
            throw new DBException();
        }
    }
    
    @Override
    public User login(String email) throws DBException {
        try (Connection conn = database.getConnection()){
            String SQL = "SELECT user_id, user_role, salt, secret FROM user "
                    + "WHERE user_email = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setString( 1, email );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                int user_id = rs.getInt("user_id");
                String user_role = rs.getString( "user_role" );
                byte[] salt = rs.getBytes("salt");
                byte[] secret = rs.getBytes("secret");
            
                User user = new User(user_id, email, User.Role.valueOf(user_role), salt, secret);
            
                return user;
            
            } else {
                throw new DBException();
            }
        } catch ( SQLException ex ) {
            throw new DBException();
        }
    }
}
