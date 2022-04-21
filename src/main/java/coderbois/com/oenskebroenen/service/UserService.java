package coderbois.com.oenskebroenen.service;

import coderbois.com.oenskebroenen.model.User;
import coderbois.com.oenskebroenen.repository.UserRepository;
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
        this.userRepository.createUser(user);
    }

    public ArrayList<User> getAllUsers() {

        return this.userRepository.getAllUsers();
    }

    public User findUserByUsername(String username) {
        return this.userRepository.findUser(username);
    }
}
