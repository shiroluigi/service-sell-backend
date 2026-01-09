package ecommerce.ecom.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.ecom.Entities.ServicesShop;
import ecommerce.ecom.Entities.User;
import ecommerce.ecom.Entities.UserOrders;
import ecommerce.ecom.common.ServicesShopCommonService;
import ecommerce.ecom.common.UserCommonService;
import ecommerce.ecom.dto.CommonDTO;
import ecommerce.ecom.dto.ServicesShopBaseDTO;
import ecommerce.ecom.dto.UserBaseDTO;
import ecommerce.ecom.dto.UserOrdersBaseDTO;

@Service
public class UserOrdersService {
    @Autowired
    UserCommonService userCommonService;
    @Autowired
    ServicesShopCommonService servicesShopCommonService;

    public CommonDTO placeOrder(UserOrdersBaseDTO orderDto) { //TODO CHECK ALL OPTIONALS AND RETURN ERR IF ERR
        UserOrders order = UserOrdersBaseDTO.toUserOrders(orderDto);
        UserBaseDTO userBaseDTO = new UserBaseDTO();
        userBaseDTO.setId(orderDto.getUser());
        Optional<List<User>> user = userCommonService.findUser(userBaseDTO);
        order.setUser(user.get().get(0));
        order.setTimestamp(LocalDateTime.now());
        ServicesShopBaseDTO serviceDto = new ServicesShopBaseDTO();
        serviceDto.setId(orderDto.getId());
        Optional<List<ServicesShop>> service = servicesShopCommonService.findService(serviceDto);
        order.setService(service.get().get(0)); 
        // actually save 
        return new CommonDTO("ORDER","OK","");
    }
    
}
