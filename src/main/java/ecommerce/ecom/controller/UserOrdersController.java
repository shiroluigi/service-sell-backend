package ecommerce.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.dto.CommonDTO;
import ecommerce.ecom.dto.UserOrdersBaseDTO;
import ecommerce.ecom.service.UserOrdersService;

@RestController
@RequestMapping("/order")
public class UserOrdersController {
    @Autowired
    UserOrdersService userOrdersService;
    @PostMapping("/place")
    public CommonDTO placeOrder(@RequestBody UserOrdersBaseDTO order){
        return userOrdersService.placeOrder(order);
    }

}
