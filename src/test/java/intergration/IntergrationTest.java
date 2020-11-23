package intergration;

import api.Api;
import core.User;
import domain.user.InvalidPassword;
import domain.user.UserExists;
import domain.user.UserNotFound;
import infrastructure.DBexception;
import infrastructure.Database;
import infrastructure.dbuser.DBUserFactory;
import infrastructure.dbuser.DBUserRepository;
import infrastructure.dbuser.DBUserServices;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@Tag("intergration-test")
public class IntergrationTest {

    Api api;

    /**
     * Before you run this script create a user 'cupcaketest' and grant access to the database:
     *
     * <pre>
     * DROP USER IF EXISTS cupcaketest@localhost;
     * CREATE USER cupcaketest@localhost;
     * GRANT ALL PRIVILEGES ON cupcaketest.* TO cupcaketest@localhost;
     * </pre>
     */
    static void resetTestDatabase() {
        String URL = "jdbc:mysql://localhost/fogtest?serverTimezone=Europe/Copenhagen&allowPublicKeyRetrieval=true";
        String USER = "fogtest";
//        String PASS = System.getenv(null);

        InputStream stream = IntergrationTest.class.getClassLoader().getResourceAsStream("init.sql");
        if (stream == null) throw new RuntimeException("init.sql");
        try (Connection conn = DriverManager.getConnection(URL, USER, null)) {
            conn.setAutoCommit(false);
            ScriptRunner runner = new ScriptRunner(conn);
            runner.setStopOnError(true);
            runner.runScript(new BufferedReader(new InputStreamReader(stream)));
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Done running migration");
    }

    @BeforeEach
    void setupAPI() {
        resetTestDatabase();

        String url = "jdbc:mysql://localhost:3306/fogtest?serverTimezone=CET";
        String user = "fogtest";

        Database db = new Database(url, user);
//        Cart cart = new Cart();
        db.runMigrations();

        DBUserFactory userFactory = new DBUserFactory(db);
        DBUserRepository userRepository = new DBUserRepository(db);
        DBUserServices userServices = new DBUserServices(db);
//        DBItemRepository itemRepository = new DBItemRepository(db);
//        DBBottomRepository bottomRepository = new DBBottomRepository(db);
//        DBToppingRepository toppingRepository = new DBToppingRepository(db);
//        LocalCartFactory cartFactory = new LocalCartFactory(cart);
//        LocalCartRepository cartRepository = new LocalCartRepository(cart);
//        LocalCartServices cartServices = new LocalCartServices(cart);
//        DBShippingFactory shippingFactory = new DBShippingFactory(db);
//        DBOrderFactory orderFactory = new DBOrderFactory(db);
//        DBOrderRepository orderRepository = new DBOrderRepository(db);
//        DBOrderServices orderServices = new DBOrderServices(db);
//
//        api = new Api(userFactory, userRepository, userServices, itemRepository, bottomRepository, toppingRepository,
//                cartFactory, cartRepository, cartServices, shippingFactory, orderFactory, orderRepository,
//                orderServices);
    }

    @Test
    void loginTest(){
        String email =  "test@test.dk";
        String password1 = "1234";
        String password2 = "1234";
        User user = null;

        //Create user
        try {
            user = api.createUser(email, password1, password2);
            assertEquals(email, user.getEmail());
        } catch (UserExists | DBexception userExists) {
            userExists.printStackTrace();
        } catch (InvalidPassword invalidPassword) {
            invalidPassword.printStackTrace();
        }

        //Login user
        try {
            user = api.login(user.getEmail(), password1);
            assertEquals(email, user.getEmail());
        } catch (InvalidPassword invalidPassword) {
            invalidPassword.printStackTrace();
        } catch (UserNotFound e){
            e.getMessage();
        } catch (DBexception dBexception) {
            dBexception.printStackTrace();
        }

        //Invalid login
        try {
            user = api.login(user.getEmail(), "2233");
        } catch (InvalidPassword invalidPassword) {
            String exceptionMessage = "" + invalidPassword.getMessage();
        } catch (UserNotFound | DBexception e){
            e.getMessage();
        }
    }

}
