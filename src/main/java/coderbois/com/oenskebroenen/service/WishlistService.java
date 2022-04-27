package coderbois.com.oenskebroenen.service;

import coderbois.com.oenskebroenen.model.Wishlist;
import coderbois.com.oenskebroenen.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public void createWishlist(Wishlist wishlist, int userId) {
        wishlist.setUserId(userId);
        this.wishlistRepository.createWishList(wishlist);
    }

    public ArrayList<Wishlist> getUserWishlists(int userId) {
        return this.wishlistRepository.findWishlistsByUserId(userId);
    }

    public Wishlist findWishlistByUserIdAndWishlistId(int userId, int wishlistId) {
        return this.wishlistRepository.findWishlistByUserIdAndWishlistId(userId, wishlistId);
    }

    public void deleteWishlistById(int id) {
        this.wishlistRepository.deleteWishlistById(id);
    }
}
