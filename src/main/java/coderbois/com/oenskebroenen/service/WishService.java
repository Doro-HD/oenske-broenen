package coderbois.com.oenskebroenen.service;

import coderbois.com.oenskebroenen.model.User;
import coderbois.com.oenskebroenen.repository.UserRepository;

import java.util.ArrayList;

public class WishService {

    private UserRepository myUsers;
    public WishService(){
        this.myUsers = new UserRepository();
    }



    public ArrayList<User> getAllUsers() {

        return this.myUsers.getAllUsers();
    }
}
