package ecommerce.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.ecom.Entities.UserOrders;

@Repository
public interface UserOrdersRepository extends JpaRepository<UserOrders, String> {
}
