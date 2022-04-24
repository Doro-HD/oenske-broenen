package coderbois.com.oenskebroenen.service;

import coderbois.com.oenskebroenen.model.Wish;
import coderbois.com.oenskebroenen.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WishService {

    private WishRepository wishRepository;

    @Autowired
    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    public void createWish(Wish wish) {
        this.wishRepository.createWish(wish);
    }

    public ArrayList<Wish> findWishesByWishlistId(int id) {
        return this.wishRepository.findWishesByWishlistId(id);
    }
}
