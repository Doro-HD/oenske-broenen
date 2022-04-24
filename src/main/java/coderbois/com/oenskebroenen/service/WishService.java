package coderbois.com.oenskebroenen.service;

import coderbois.com.oenskebroenen.model.Wish;
import coderbois.com.oenskebroenen.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void findWishesByWishlistId(int id) {
        this.wishRepository.findWishesByWishlistId(id);
    }
}
