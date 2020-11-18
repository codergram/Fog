package infrastructure.dbuser;

import core.User;
import domain.user.UserRepository;
import infrastructure.DBexception;
import infrastructure.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBUserRepository implements UserRepository {

    private final Database database;

    public DBUserRepository(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<User> getAllUsersFromDB() throws DBexception {
        ArrayList<User> allUsersFromDB = new ArrayList<>();

        try (Connection conn = database.getConnection()){

            String SQL = "SELECT user_id, user_email, user_role FROM user "
                    + "WHERE user_active = 1";

            PreparedStatement ps = conn.prepareStatement( SQL );

            ResultSet rs = ps.executeQuery();

            while ( rs.next() ) {
                int user_id = rs.getInt("user_id");
                String user_email = rs.getString( "user_email" );
                String user_role = rs.getString( "user_role" );

                User user = new User( user_id, user_email, user_role);

                allUsersFromDB.add(user);
            }
        } catch ( SQLException ex ) {
            throw new DBexception();
        }

        return allUsersFromDB;
    }

    @Override
    public User getUserById(int user_id) throws DBexception {
        User user = null;

        try (Connection conn = database.getConnection()){

            String SQL = "SELECT user_email, user_role FROM user "
                    + "WHERE user_id = ?";

            PreparedStatement ps = conn.prepareStatement( SQL );
            ps.setInt( 1, user_id );
            ResultSet rs = ps.executeQuery();

            if ( rs.next() ) {
                String user_email = rs.getString( "user_email" );
                String user_role = rs.getString( "user_role" );

                user = new User( user_id, user_email, user_role);

                return user;
            }
        } catch ( SQLException ex ) {
            throw new DBexception();
        }

        return user;
    }
}
