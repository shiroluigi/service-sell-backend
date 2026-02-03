package ecommerce.ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.ecom.Models.ServicesShop;

@Repository
public interface ServicesShopRepository extends JpaRepository<ServicesShop, Integer> {
}
