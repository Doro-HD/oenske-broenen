package coderbois.com.oenskebroenen.security;

import coderbois.com.oenskebroenen.model.User;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PasswordManagerTest {

    @Test
    void validatePassword() {
        //Set up
        PasswordManager passwordManager = new PasswordManager();
        final String RIGHT_PASSWORD = "123";
        final String WRONG_PASSWORD = "test";

        User user = new User("Kenta", RIGHT_PASSWORD);
        String hash = passwordManager.createHashedPassword(user.getPassword());

        boolean isValid = passwordManager.validatePassword(user.getPassword(), hash);

        //assert if the users password matches the hash created from the password
        assertTrue(isValid);

        //enter new password that should fail when matched with the hash
        isValid = passwordManager.validatePassword(WRONG_PASSWORD, hash);

        //assert if the newly entered password matches the password used to create the user
        assertFalse(isValid);
    }
}