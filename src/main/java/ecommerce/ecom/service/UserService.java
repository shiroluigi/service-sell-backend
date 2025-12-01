package ecommerce.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ecommerce.ecom.dto.User;
import ecommerce.ecom.repository.UserRepository;

@Component
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addUser(User newUser){
        userRepository.save(newUser);
        return newUser;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }
}
