package ecommerce.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.ecom.Entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
    List<User> findAllByEmail(String email);  
}
