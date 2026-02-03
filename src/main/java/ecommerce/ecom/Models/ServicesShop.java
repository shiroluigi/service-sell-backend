package ecommerce.ecom.Models;

import ecommerce.ecom.Enums.IsActiveEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "service_store_products")
public class ServicesShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String service_name;
    private int price; 
    private String currency;
    private String description;
    private String duration;
    @Enumerated(EnumType.STRING)
    private IsActiveEnum status;
}
