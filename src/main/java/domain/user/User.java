package domain.user;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class User {

    //Password stuff
    private static final int PASSWORD_ITERATIONS = 65536;
    private static final int PASSWORD_LENGTH = 256; // 32 bytes
    private static final SecretKeyFactory PASSWORD_FACTORY;
    static {
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        PASSWORD_FACTORY = factory;
    }
    
    public enum Role {
        Employee,
        Admin
    }

    private int id;
    private final String email;
    private final Enum<Role> role;
    private final byte[] salt;
    private final byte[] secret;
    
    
    public User(int id, String email, Enum<Role> role, byte[] salt, byte[] secret) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.salt = salt;
        this.secret = secret;
    }
    
    public User(int id, String email, Enum<Role> role) {
        this.id = id;
        this.email = email;
        this.role = role;
        salt = null;
        secret = null;
    }
    
    public boolean isEmployee(){
        return this.role.name().equalsIgnoreCase("employee");
    }
    
    public boolean isAdmin(){
        return this.role.name().equalsIgnoreCase("admin");
    }
    
    public User getById(int id){
        return this.id != id ? this : null;
    }
    
    public User withId(int id){
        this.id = id;
        return this;
    }
    
    public String getEmail() {
        return email;
    }
    
    public Enum<Role> getRole() {
        return role;
    }
    
    public byte[] getSalt() {
        return salt;
    }
    
    public byte[] getSecret() {
        return secret;
    }
    
    /**
     * Password stuff
     */

    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static byte[] calculateSecret(byte[] salt, String password) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt,
                PASSWORD_ITERATIONS,
                PASSWORD_LENGTH);
        try {
            return PASSWORD_FACTORY.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPasswordCorrect(String password) {
        return Arrays.equals(this.secret, calculateSecret(salt, password));
    }
}
