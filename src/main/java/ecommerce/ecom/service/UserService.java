package ecommerce.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ecommerce.ecom.Entities.User;
import ecommerce.ecom.dto.UserLoginDTO;
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

    public UserLoginDTO login(UserLoginDTO loginUser) {System.out.println("UserService.getUsers() called");
        Optional<User> userOptional = userRepository.findByEmail(loginUser.getEmail());
        if (userOptional.isPresent()){
            User user = userOptional.get();
            UserLoginDTO userLoginDto = UserLoginDTO.toDto(user);
            return userLoginDto;
        }else{       
            UserLoginDTO user = new UserLoginDTO();
            user.setErrMsg("User login not found");
            return user;
        }
    }
}
