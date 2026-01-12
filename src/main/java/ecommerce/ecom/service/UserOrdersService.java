package ecommerce.ecom.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import ecommerce.ecom.enums.OrderStatusEnum;
import ecommerce.ecom.repository.UserOrdersRepository;

@Service
public class UserOrdersService {
    @Autowired
    UserCommonService userCommonService;
    @Autowired
    ServicesShopCommonService servicesShopCommonService;
    @Autowired
    UserOrdersRepository userOrdersRepository;

    public ResponseEntity<CommonDTO> placeOrder(UserOrdersBaseDTO orderDto) {
        try {
            // Convert the DTO to UserOrders object to save
            UserOrders order = UserOrdersBaseDTO.toUserOrders(orderDto);
            // Get a new UserBaseDTO as userCommonService.findUser(UserBaseDTO)
            UserBaseDTO userBaseDTO = new UserBaseDTO();
            userBaseDTO.setId(orderDto.getUser());
            List<User> user = userCommonService.findUser(userBaseDTO);
            // If no user returned then return error
            if (user.isEmpty()) {
                return new ResponseEntity<>(new CommonDTO("ORDER", "ERROR", "User Not Found"), HttpStatus.BAD_REQUEST);
            }
            ServicesShopBaseDTO serviceDto = new ServicesShopBaseDTO();
            serviceDto.setId(orderDto.getService());
            Optional<ServicesShop> service = servicesShopCommonService.findService(serviceDto);
            // If no service returned then return error
            if (service.isEmpty()) {
                return new ResponseEntity<>(new CommonDTO("ORDER", "ERROR", "Service Not Found"),
                        HttpStatus.BAD_REQUEST);
            }
            // Check if this user has the same service ordered and order status is not DELIVERED
            List<UserOrders> duplicateOrders = userOrdersRepository.hasUserAndServiceId(user.get(0), service.get());
            for(UserOrders o : duplicateOrders){
                if (!(o.getOrderStatus() == OrderStatusEnum.DELIVERED)){
                    return new ResponseEntity<>(new CommonDTO("ORDER", "ERROR", "Same order exists and is still not delivered. Please wait for respose or request cancellation first."),
                        HttpStatus.CONFLICT);
                }
            }
            order.setUser(user.get(0));
            order.setTimestamp(LocalDateTime.now());
            
            order.setService(service.get());
            order.setPrice(service.get().getCurrency() +" "+ service.get().getPrice());
            // Persist
            userOrdersRepository.save(order);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(new CommonDTO("ORDER", "OK", ""), HttpStatus.OK);
    }

    public ResponseEntity<List<UserOrdersBaseDTO>> getUserOrders(UserBaseDTO user) {
        List<UserOrdersBaseDTO> orders = new ArrayList<>();
        try {
            List<UserOrders> ordersRaw = userOrdersRepository.findAllByUserId(user.getId());
            for (UserOrders o : ordersRaw) {
                UserOrdersBaseDTO uob = UserOrdersBaseDTO.toDto(o);
                orders.add(uob);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(orders, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    public ResponseEntity<UserOrdersBaseDTO> getOrderUsingId(String orderId) {
        Optional<UserOrders> order = userOrdersRepository.findById(orderId);
        if(order.isPresent()){
            return new ResponseEntity<>(UserOrdersBaseDTO.toDto(order.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
