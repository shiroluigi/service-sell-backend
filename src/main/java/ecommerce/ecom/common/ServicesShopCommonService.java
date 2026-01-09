package ecommerce.ecom.common;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ecommerce.ecom.Entities.ServicesShop;
import ecommerce.ecom.dto.ServicesShopBaseDTO;
import ecommerce.ecom.repository.ServicesShopRepository;

@Component
public class ServicesShopCommonService {
    @Autowired
    ServicesShopRepository servicesShopRepository;

     public Optional<List<ServicesShop>> findService(ServicesShopBaseDTO serviceDto){
        Optional<List<ServicesShop>> res = null;
        // Add other conditions
        if (serviceDto.getId() != ""){
            try{
                ServicesShop u = servicesShopRepository.findById(Integer.parseInt(serviceDto.getId())).get(); //UNSAFE
                List<ServicesShop> l = Arrays.asList(u);
                return Optional.ofNullable(l);
            }catch (Exception e){
                System.out.print("Error in Parsing Integer form String to Int in findService"); //hit
                return Optional.ofNullable(null);
            }
        }
        
        return res;
    }
}
