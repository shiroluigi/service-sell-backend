package ecommerce.ecom.Common;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ecommerce.ecom.DTO.ServicesShopBaseDTO;
import ecommerce.ecom.Models.ServicesShop;
import ecommerce.ecom.Repository.ServicesShopRepository;

@Component
public class ServicesShopCommonService {
    @Autowired
    ServicesShopRepository servicesShopRepository;

     public Optional<ServicesShop> findService(ServicesShopBaseDTO serviceDto){
        ServicesShop res = null;
        if (serviceDto.getId() != ""){
            try{
                res = servicesShopRepository.findById(Integer.parseInt(serviceDto.getId())).get();
            }catch (Exception e){
                System.out.print(e.getMessage());
                return Optional.ofNullable(res);
            }
        }
        return Optional.ofNullable(res);
    }
}
