package ecommerce.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.ecom.Entities.ServicesShop;

@Repository
public interface ServicesShopRepository extends JpaRepository<ServicesShop, Integer> {
}
