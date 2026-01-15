package ecommerce.ecom.controller.open;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.dto.CommonDTO;
import ecommerce.ecom.dto.UserBaseDTO;
import ecommerce.ecom.service.UserService;

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
