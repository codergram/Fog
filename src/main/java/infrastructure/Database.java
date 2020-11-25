package infrastructure;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.*;

public class Database {
    private final String URL;
    private final String USER;
    private final String PSW;

    // Database version
    private static final int version = 2;

    public Database(String url, String user, String psw) {
        this.URL = url == null ? "jdbc:mysql://localhost:3306/fog?serverTimezone=CET" : url;
        this.USER = user == null ? "fog" : user;
        this.PSW = psw == null ? "codergram" : psw;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Database() {
        this(null, null, null);
    }

    public void runMigrations() {
        try {
            int currentVersion = getCurrentVersion();
            while (currentVersion < version) {
                System.out.printf("Current DB version %d is smaller than expected %d\n", currentVersion, version);
                runMigration(currentVersion + 1);
                int new_version = getCurrentVersion();
                if (new_version > currentVersion) {
                    currentVersion = new_version;
                    System.out.println("Updated database to version: " + new_version);
                } else {
                    throw new RuntimeException("Something went wrong, version not increased: " + new_version);
                }
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void runMigration(int i) throws IOException, SQLException {
        String migrationFile = String.format("migrate/%d.sql", i);
        System.out.println("Running migration: " + migrationFile);
        InputStream stream = Database.class.getClassLoader().getResourceAsStream(migrationFile);
        if (stream == null) {
            System.out.println("Migration file, does not exist: " + migrationFile);
            throw new FileNotFoundException(migrationFile);
        }
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setStopOnError(true);
            runner.runScript(new BufferedReader(new InputStreamReader(stream)));
            conn.commit();
        }
        System.out.println("Done running migration");
    }

    public int getCurrentVersion() {
        try (Connection conn = getConnection()) {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT value FROM properties WHERE name = 'version';");
            if (rs.next()) {
                String column = rs.getString("value");
                return Integer.parseInt(column);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return -1;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PSW);
    }

    public static int getVersion() {
        return version;
    }

}

