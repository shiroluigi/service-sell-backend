package ecommerce.ecom.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ecommerce.ecom.Common.EmailService;
import ecommerce.ecom.Common.ServicesShopCommonService;
import ecommerce.ecom.Common.UserCommonService;
import ecommerce.ecom.DTO.CommonDTO;
import ecommerce.ecom.DTO.ServicesShopBaseDTO;
import ecommerce.ecom.DTO.UserBaseDTO;
import ecommerce.ecom.DTO.UserOrdersBaseDTO;
import ecommerce.ecom.Enums.OrderStatusEnum;
import ecommerce.ecom.Enums.PaymentStatusEnum;
import ecommerce.ecom.Models.ServicesShop;
import ecommerce.ecom.Models.User;
import ecommerce.ecom.Models.UserOrders;
import ecommerce.ecom.Repository.UserOrdersRepository;
import tools.jackson.databind.ObjectMapper;

@Service
public class UserOrdersService {
    @Autowired
    private UserCommonService userCommonService;
    @Autowired
    private ServicesShopCommonService servicesShopCommonService;
    @Autowired
    private UserOrdersRepository userOrdersRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    ObjectMapper objectMapper;

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
            // Check if this user has the same service ordered and order status is not
            // DELIVERED
            List<UserOrders> duplicateOrders = userOrdersRepository.hasUserAndServiceId(user.get(0), service.get());
            for (UserOrders o : duplicateOrders) {
                if (!(o.getOrderStatus() == OrderStatusEnum.COMPLETED)) {
                    return new ResponseEntity<>(new CommonDTO("ORDER", "ERROR",
                            "Same order exists and is still not delivered. Please wait for respose or request cancellation first."),
                            HttpStatus.CONFLICT);
                }
            }
            order.setUser(user.get(0));
            order.setTimestamp(LocalDateTime.now());
            order.setService(service.get());
            order.setPrice(service.get().getCurrency() + " " + service.get().getPrice());
            // Persist
            UserOrders savedOrder = userOrdersRepository.save(order);
            // Make this more graceful
            emailService.sendEmail(user.get(0).getEmail(), "Order " + savedOrder.getId(),
                    "Congratulations your order is placed! Please wait for reply from the team.");
            emailService.sendEmail("rohit.luiji3@gmail.com", "New Order " + savedOrder.getId(),
                    objectMapper.writeValueAsString(savedOrder));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(new CommonDTO("ORDER", "OK", ""), HttpStatus.OK);
    }

    public ResponseEntity<?> getUserOrders(UserBaseDTO user) {
        List<UserOrdersBaseDTO> orders = new ArrayList<>();
        try {
            List<UserOrders> ordersRaw = userOrdersRepository.findAllByUserId(user.getId());
            for (UserOrders o : ordersRaw) {
                UserOrdersBaseDTO uob = UserOrdersBaseDTO.toDto(o);
                orders.add(uob);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new CommonDTO("ORDER", "ERROR", "Something went wrong"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    public ResponseEntity<?> getOrderUsingId(String orderId) {
        Optional<UserOrders> order = userOrdersRepository.findById(orderId);
        if (order.isPresent()) {
            return new ResponseEntity<>(UserOrdersBaseDTO.toDto(order.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new CommonDTO("ORDER", "ERROR", "Order not found"), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> getAllOrders() {
        return new ResponseEntity<>(userOrdersRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> editSingleOrder(UserOrdersBaseDTO orderDto) {
        Optional<UserOrders> orderOptional = userOrdersRepository.findById(orderDto.getId());
        if (orderOptional.isEmpty()) {
            return new ResponseEntity<>(new CommonDTO("Edit Order", "Not Found", "Order with ID doesnot exist"),
                    HttpStatus.NOT_FOUND);
        }
        UserOrders order = orderOptional.get();
        try {
            //TODO: implement other edits
            if (orderDto.getPaymentStatus() != null && !orderDto.getPaymentStatus().isBlank()) {
                order.setPaymentStatus(PaymentStatusEnum.valueOf(orderDto.getPaymentStatus()));
            }
            if (orderDto.getOrderStatus() != null && !orderDto.getOrderStatus().isBlank()) {
                order.setOrderStatus(OrderStatusEnum.valueOf(orderDto.getOrderStatus()));
            }
            userOrdersRepository.save(order);
            return new ResponseEntity<>(new CommonDTO("Edit Order", "Success", ""), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CommonDTO("Edit Order", "Error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }
}
