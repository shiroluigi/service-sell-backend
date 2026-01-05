package ecommerce.ecom.dto;

import ecommerce.ecom.Entities.ServicesShop;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicesShopBaseDTO {
    private int id;
    private String service_name;
    private int price; 
    private String currency;
    private String description;

    public static ServicesShopBaseDTO toDto(ServicesShop s){
        ServicesShopBaseDTO c = new ServicesShopBaseDTO();
        c.setCurrency(s.getCurrency());
        c.setDescription(s.getDescription());
        c.setId(s.getId());
        c.setPrice(s.getPrice());
        c.setService_name(s.getService_name());
        return c;
    }
}
