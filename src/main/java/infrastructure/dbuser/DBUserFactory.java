package infrastructure.dbuser;

import core.User;
import domain.user.UserFactory;
import infrastructure.DBexception;
import infrastructure.Database;

import java.sql.*;

public class DBUserFactory implements UserFactory {

    private final Database database;

    public DBUserFactory(Database database) {
        this.database = database;
    }

    @Override
    public User createUser(User user) throws DBexception {
        try (Connection conn = database.getConnection()){

            //Prepare a SQL statement from the DB connection
            String SQL = "INSERT INTO user (user_email, user_role, salt, secret) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement( SQL, Statement.RETURN_GENERATED_KEYS );

            //Link variables to the SQL statement
            ps.setString(1, user.getUserEmail());
            ps.setString(2, user.getUserRole());
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
            throw new DBexception();
        }
    }
}
