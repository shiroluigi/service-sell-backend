package ecommerce.ecom.controller.closed;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/authcheck")
    public ResponseEntity<?> authenticationCheck(){
        return new ResponseEntity<>("Authenticated",HttpStatus.OK);
    }
    @GetMapping("/roles")
    public ResponseEntity<?> getRoles(){
        return userService.getRoles();
    }
}
