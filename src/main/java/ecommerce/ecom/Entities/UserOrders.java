package ecommerce.ecom.Entities;

import java.time.LocalDateTime;


import ecommerce.ecom.enums.OrderStatusEnum;
import ecommerce.ecom.enums.PaymentStatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_orders")
public class UserOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime timestamp;
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum orderStatus;
    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum paymentStatus;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServicesShop service;
    private String refundUpi;
    private String fullName;
    private String paymentReference;
    private String phone;
    private String projectRequirements;
    private String price;
}
