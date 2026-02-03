package ecommerce.ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ecommerce.ecom.Models.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, String> {
}
