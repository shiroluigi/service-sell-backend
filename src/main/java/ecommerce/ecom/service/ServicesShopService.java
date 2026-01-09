package ecommerce.ecom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.ecom.Entities.ServicesShop;
import ecommerce.ecom.dto.ServicesShopResponseDTO;
import ecommerce.ecom.repository.ServicesShopRepository;

@Service
public class ServicesShopService {
    @Autowired
    private ServicesShopRepository servicesShopRepository;

    public List<ServicesShopResponseDTO> getAllServices() {
        List<ServicesShop> servicesRaw = servicesShopRepository.findAll();
        List<ServicesShopResponseDTO> services = new ArrayList<ServicesShopResponseDTO>();
        for (ServicesShop service : servicesRaw){
            ServicesShopResponseDTO converted = ServicesShopResponseDTO.toDto(service);
            services.add(converted);
        }
        return services;
    }

    public ServicesShopResponseDTO getSingleService(int id) {
        Optional<ServicesShop> service = servicesShopRepository.findById(id);
        if(service.isPresent()){
            return ServicesShopResponseDTO.toDto(service.get());
        }else{
            return new ServicesShopResponseDTO("Not Found");
        }
    }
}
