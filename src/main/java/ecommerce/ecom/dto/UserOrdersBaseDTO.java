package ecommerce.ecom.dto;

import ecommerce.ecom.Entities.UserOrders;
import ecommerce.ecom.enums.OrderStatusEnum;
import ecommerce.ecom.enums.PaymentStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOrdersBaseDTO {
    private String id;
    private String user;
    private String timestamp;
    private String orderStatus;
    private String paymentStatus;
    private String service;
    private String refundUpi;
    private String fullName;
    private String paymentReference;
    private String phone;
    private String projectRequirements;
    private String price;

    public static UserOrders toUserOrders(UserOrdersBaseDTO dto){
        UserOrders userOrders = new UserOrders();
        userOrders.setFullName(dto.getFullName());
        userOrders.setPaymentReference(dto.getPaymentReference());
        userOrders.setPhone(dto.getPhone());
        userOrders.setProjectRequirements(dto.getProjectRequirements());
        userOrders.setOrderStatus(OrderStatusEnum.ORDER_PLACED);
        userOrders.setPaymentStatus(PaymentStatusEnum.PENDING_APPROVAL);
        userOrders.setRefundUpi(dto.getRefundUpi());
        return userOrders;
    }
    public static UserOrdersBaseDTO toDto(UserOrders obj){
        UserOrdersBaseDTO userOrders = new UserOrdersBaseDTO();
        userOrders.setId(obj.getId());
        userOrders.setTimestamp(obj.getTimestamp().toString());
        userOrders.setOrderStatus(obj.getOrderStatus().toString());
        userOrders.setPaymentStatus(obj.getPaymentStatus().toString());
        userOrders.setFullName(obj.getFullName());
        userOrders.setPaymentReference(obj.getPaymentReference());
        userOrders.setPhone(obj.getPhone());
        userOrders.setProjectRequirements(obj.getProjectRequirements());
        userOrders.setPrice(obj.getPrice());
        userOrders.setService(obj.getService().getService_name());
        return userOrders;
    }
}
