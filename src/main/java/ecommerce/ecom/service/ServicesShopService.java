package ecommerce.ecom.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.ecom.Entities.ServicesShop;
import ecommerce.ecom.dto.ServicesShopBaseDTO;
import ecommerce.ecom.repository.ServicesShopRepository;

@Service
public class ServicesShopService {
    @Autowired
    private ServicesShopRepository servicesShopRepository;

    public List<ServicesShopBaseDTO> getAllServices() {
        List<ServicesShop> servicesRaw = servicesShopRepository.findAll();
        List<ServicesShopBaseDTO> services = new ArrayList<ServicesShopBaseDTO>();
        for (ServicesShop service : servicesRaw){
            ServicesShopBaseDTO converted = ServicesShopBaseDTO.toDto(service);
            services.add(converted);
        }
        return services;
    }
}
