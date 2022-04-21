package coderbois.com.oenskebroenen.service;

import coderbois.com.oenskebroenen.model.User;
import coderbois.com.oenskebroenen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private UserRepository myUsers;

    @Autowired
    public UserService(){
        this.myUsers = new UserRepository();
    }



    public ArrayList<User> getAllUsers() {

        return this.myUsers.getAllUsers();
    }
}
