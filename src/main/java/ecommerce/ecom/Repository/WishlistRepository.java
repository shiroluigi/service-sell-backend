package ecommerce.ecom.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ecommerce.ecom.Models.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, String> {

    @Query("select wl from Wishlist wl where wl.user.id = :uid")
    public List<Wishlist> findByUserId(@Param("uid") String userId);
}
