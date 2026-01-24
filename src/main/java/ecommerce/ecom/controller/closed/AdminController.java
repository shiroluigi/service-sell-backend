package ecommerce.ecom.controller.closed;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.dto.UserOrdersBaseDTO;
import ecommerce.ecom.service.UserOrdersService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserOrdersService userOrdersService;

    @GetMapping("/orders/all")
    public ResponseEntity<?> getAllOrders(){
        return userOrdersService.getAllOrders();
    }

    @PostMapping("/order/edit")
    public ResponseEntity<?> editSingleOrder(@RequestBody UserOrdersBaseDTO order){
        return userOrdersService.editSingleOrder(order);
    }
}
