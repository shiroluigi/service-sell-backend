package ecommerce.ecom.dto;

import ecommerce.ecom.Entities.ServicesShop;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServicesShopResponseDTO extends ServicesShopBaseDTO {
    String errMsg;
    public ServicesShopResponseDTO(String errMsg){
        this.errMsg = errMsg;
    }
    public static ServicesShopResponseDTO toDto(ServicesShop service){
        ServicesShopResponseDTO converted = new ServicesShopResponseDTO();
        converted.setCurrency(service.getCurrency());
        converted.setDescription(service.getDescription());
        converted.setId(""+service.getId());
        converted.setPrice(""+service.getPrice());
        converted.setService_name(service.getService_name());
        converted.setDuration(service.getDuration());
        return converted;
    }
}
