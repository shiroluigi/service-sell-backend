package ecommerce.ecom.dto;

import ecommerce.ecom.Entities.ServicesShop;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicesShopBaseDTO {
    private String id;
    private String service_name;
    private String price; 
    private String currency;
    private String description;
    private String duration;

    public static ServicesShopBaseDTO toDto(ServicesShop s){
        ServicesShopBaseDTO c = new ServicesShopBaseDTO();
        c.setCurrency(s.getCurrency());
        c.setDescription(s.getDescription());
        c.setId(""+s.getId());
        c.setPrice(""+s.getPrice());
        c.setService_name(s.getService_name());
        c.setDuration(s.getDuration());
        return c;
    }
    public static ServicesShop toServicesShop(ServicesShopBaseDTO s){
        ServicesShop c = new ServicesShop();
        c.setCurrency(s.getCurrency());
        c.setDescription(s.getDescription());
        c.setPrice(Integer.parseInt(s.getPrice()));
        c.setService_name(s.getService_name());
        c.setDuration(s.getDuration());
        return c;
    }
}
