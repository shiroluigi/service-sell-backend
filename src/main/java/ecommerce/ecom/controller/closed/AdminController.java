package ecommerce.ecom.controller.closed;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.service.UserOrdersService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserOrdersService userOrdersService;

    @GetMapping("/orders/all")
    public ResponseEntity<?> getAllOrders(HttpServletRequest request){
        return userOrdersService.getAllOrders();
    }
}
