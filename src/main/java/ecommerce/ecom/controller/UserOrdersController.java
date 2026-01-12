package ecommerce.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.dto.CommonDTO;
import ecommerce.ecom.dto.UserBaseDTO;
import ecommerce.ecom.dto.UserOrdersBaseDTO;
import ecommerce.ecom.service.UserOrdersService;

@RestController
@RequestMapping("/order")
public class UserOrdersController {
    @Autowired
    UserOrdersService userOrdersService;

    @PostMapping("/place")
    public ResponseEntity<CommonDTO> placeOrder(@RequestBody UserOrdersBaseDTO order){
        return userOrdersService.placeOrder(order);
    }
    @PostMapping("/user")
    public ResponseEntity<List<UserOrdersBaseDTO>> getUserOrders(@RequestBody UserBaseDTO user){
        return userOrdersService.getUserOrders(user);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<UserOrdersBaseDTO> getOrderUsingId(@PathVariable String orderId){
        return userOrdersService.getOrderUsingId(orderId);
    }
}
