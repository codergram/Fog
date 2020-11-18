package infrastructure.dbuser;

import core.User;
import domain.user.UserServices;
import infrastructure.DBexception;
import infrastructure.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUserServices implements UserServices {

    private final Database database;

    public DBUserServices(Database database) {
        this.database = database;
    }

    @Override
    public void updateUserById(int user_id, String user_role, double user_credit) throws DBexception {

        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE user SET user_role = ?, user_credit = ? "
                    + " WHERE user_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );

            //Link variables to the SQL statement
            ps.setString(1, user_role);
            ps.setDouble(2, user_credit);
            ps.setInt(3, user_id);

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

        } catch ( SQLException ex ) {
            throw new DBexception();
        }
    }

    @Override
    public void deleteUserById(int user_id) throws DBexception {

        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE user SET user_active = 0 "
                    + " WHERE user_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );

            //Link variables to the SQL statement
            ps.setInt(1, user_id);

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

        } catch ( SQLException ex ) {
            throw new DBexception();
        }
    }

    @Override
    public void changeUserRoleToCustomer(int user_id) throws DBexception {

        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE user SET user_role = 'customer' "
                    + " WHERE user_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );

            //Link variables to the SQL statement
            ps.setInt(1, user_id);

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

        } catch ( SQLException ex ) {
            throw new DBexception();
        }
    }

    @Override
    public void changeUserRoleToAdmin(int user_id) throws DBexception {

        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "UPDATE user SET user_role = 'admin' "
                    + " WHERE user_id = ? ";
            PreparedStatement ps = conn.prepareStatement( SQL );

            //Link variables to the SQL statement
            ps.setInt(1, user_id);

            //Execute the SQL statement to update the DB
            ps.executeUpdate();

        } catch ( SQLException ex ) {
            throw new DBexception();
        }
    }

    @Override
    public boolean userAldreadyExistsInDB(String email) throws DBexception {

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
            throw new DBexception();
        }
    }

    @Override
    public User login(String user_email) throws DBexception {

        try (Connection conn = database.getConnection()){
            String SQL = "SELECT user_id, user_role, salt, secret FROM user "
                    + "WHERE user_email = ?";
            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setString( 1, user_email );
            ResultSet rs = ps.executeQuery();
            if ( rs.next() ) {
                int user_id = rs.getInt("user_id");
                String user_role = rs.getString( "user_role" );
                byte[] salt = rs.getBytes("salt");
                byte[] secret = rs.getBytes("secret");

                User user = new User(user_id, user_email, user_role, salt, secret);

                return user;

            } else {
                throw new DBexception();
            }
        } catch ( SQLException ex ) {
            throw new DBexception();
        }
    }

}
