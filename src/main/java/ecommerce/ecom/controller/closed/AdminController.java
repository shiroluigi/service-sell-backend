package ecommerce.ecom.controller.closed;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.dto.UserBaseDTO;
import ecommerce.ecom.dto.UserOrdersBaseDTO;
import ecommerce.ecom.service.UserOrdersService;
import ecommerce.ecom.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserOrdersService userOrdersService;
    @Autowired
    private UserService userService;

    @GetMapping("/orders/all")
    public ResponseEntity<?> getAllOrders(){
        return userOrdersService.getAllOrders();
    }

    @PostMapping("/order/edit")
    public ResponseEntity<?> editSingleOrder(@RequestBody UserOrdersBaseDTO order){
        return userOrdersService.editSingleOrder(order);
    }
    @GetMapping("/users/all")
    public ResponseEntity<?> getAllUsers(){
        return userService.getUsers();
    }
    @PostMapping("/users/add")
    public ResponseEntity<?> addUserAdmin(@RequestBody UserBaseDTO user){
        return userService.addUserAdmin(user);
    }
    @PostMapping("/users/delete")
    public ResponseEntity<?> deleteUserAdmin(@RequestBody UserBaseDTO user){
        return userService.deleteUserAdmin(user);
    }
    @PostMapping("/users/edit")
    public ResponseEntity<?> editUser(@RequestBody UserBaseDTO user){
        return userService.editUser(user);
    }
}
