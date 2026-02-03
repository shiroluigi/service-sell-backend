package ecommerce.ecom.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ecommerce.ecom.Models.ServicesShop;
import ecommerce.ecom.Models.User;
import ecommerce.ecom.Models.UserOrders;

@Repository
public interface UserOrdersRepository extends JpaRepository<UserOrders, String> {

    List<UserOrders> findAllByUserId(String id);

    @Query("""
            select uo
            from UserOrders uo
            where uo.user = :user and uo.service = :service
            """)
    List<UserOrders> hasUserAndServiceId(User user, ServicesShop service);
}
