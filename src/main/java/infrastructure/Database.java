package infrastructure;

import java.sql.*;
import java.util.TimeZone;

public class Database {
    
    // Database configuration
    static final String DB_SERVER = "localhost";
    static final String DB_USER = "webappuser";
    static final String DB_PASS = "webapppassword";
    static final String SCHEMA_NAME = "Webapp";
    

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://" + DB_SERVER + "/" + SCHEMA_NAME + "?serverTimezone=" + TimeZone.getDefault().getID();
    
    
    // Database version
    private static final int version = 1;
    
    public Database() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        if (getCurrentVersion() != getVersion()) {
            throw new IllegalStateException("Database in wrong state, expected:"
                    + getVersion() + ", got: " + getCurrentVersion());
        }
    }
    
    public static int getCurrentVersion() {
        try (Connection conn = getConnection()) {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT value FROM properties WHERE name = 'version';");
            if(rs.next()) {
                String column = rs.getString("value");
                return Integer.parseInt(column);
            } else {
                System.err.println("No version in properties.");
                return -1;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        
    }
    
    public static int getVersion() {
        return version;
    }
}
