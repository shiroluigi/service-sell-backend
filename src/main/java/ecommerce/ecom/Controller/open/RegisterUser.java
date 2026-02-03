package ecommerce.ecom.Controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.DTO.CommonDTO;
import ecommerce.ecom.DTO.UserBaseDTO;
import ecommerce.ecom.Service.UserService;

@RestController
@RequestMapping("/public/register")
public class RegisterUser {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<CommonDTO> registerUser(@RequestBody UserBaseDTO newUser) {
        return userService.registerUser(newUser);
    }
}
