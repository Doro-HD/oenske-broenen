package coderbois.com.oenskebroenen.security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordManager {

    public String createHashedPassword(String password) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(password, salt);
    }

    public boolean validatePassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
