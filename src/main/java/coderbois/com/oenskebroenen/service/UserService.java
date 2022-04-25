package coderbois.com.oenskebroenen.service;

import coderbois.com.oenskebroenen.model.User;
import coderbois.com.oenskebroenen.repository.UserRepository;
import coderbois.com.oenskebroenen.security.PasswordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(){
        this.userRepository = new UserRepository();
    }

    public void createUser(User user) {
        PasswordManager passwordManager = new PasswordManager();
        String hashedPassword = passwordManager.createHashedPassword(user.getPassword());

        user.setPassword(hashedPassword);
        this.userRepository.createUser(user);
    }

    public ArrayList<User> getAllUsers() {

        return this.userRepository.getAllUsers();
    }

    public User findUserByUsername(String username) {
        return this.userRepository.findUser(username);
    }
}
