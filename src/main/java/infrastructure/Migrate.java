package infrastructure;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Migrate {
    
    Database database = new Database();
//    !!!Remember to change the DB name in the Connector!!!
    
    public static void main(String[] args) throws IOException, SQLException {
        new Migrate().runMigrations();
    }
    
    public void runMigrations() throws IOException, SQLException {
        int version = database.getCurrentVersion();
        while (version < Database.getVersion()) {
            System.out.printf("Current DB version %d is smaller than expected %d%n", version, Database.getVersion());
            runMigration(version + 1);
            int newVersion = database.getCurrentVersion();
            if (newVersion > version) {
                version = newVersion;
                System.out.println("Updated database to version: " + newVersion);
            } else {
                throw new RuntimeException("Something went wrong, version not increased: " + newVersion);
            }
        }
    }
    
    public void runMigration(int i) throws IOException, SQLException {
        String migrationFile = String.format("migrate/%d.sql", i);
        System.out.println("Running migration: " + migrationFile);
        InputStream stream = Migrate.class.getClassLoader().getResourceAsStream(migrationFile);
        if (stream == null) {
            System.out.println("Migration file, does not exist: " + migrationFile);
            throw new FileNotFoundException(migrationFile);
        }
        try (Connection conn = database.getConnection()) {
            conn.setAutoCommit(false);
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setStopOnError(true);
            runner.runScript(new BufferedReader(new InputStreamReader(stream)));
            conn.commit();
        }
        System.out.println("Done running migration");
    }
}
