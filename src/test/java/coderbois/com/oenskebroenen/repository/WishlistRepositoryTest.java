package coderbois.com.oenskebroenen.repository;

import coderbois.com.oenskebroenen.model.Wishlist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WishlistRepositoryTest {

    @Test
    void createWishList() {
        Wishlist wishlist = new Wishlist("testing", "new testing", 1);
        WishlistRepository wishlistRepository = new WishlistRepository();

        wishlistRepository.createWishList(wishlist);
    }
}