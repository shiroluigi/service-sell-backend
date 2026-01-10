package ecommerce.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.Entities.User;
import ecommerce.ecom.dto.CommonDTO;
import ecommerce.ecom.dto.UserBaseDTO;
import ecommerce.ecom.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public List<User> getAllUsers(){
        return userService.getUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<UserBaseDTO> loginUser(@RequestBody UserBaseDTO loginUser){
        return userService.login(loginUser);
    }

    @PostMapping("/register")
    public ResponseEntity<CommonDTO> registerUser(@RequestBody UserBaseDTO newUser){
        return userService.registerUser(newUser);
    }
}
