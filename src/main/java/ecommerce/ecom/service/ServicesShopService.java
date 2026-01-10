package ecommerce.ecom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ecommerce.ecom.Entities.ServicesShop;
import ecommerce.ecom.dto.ServicesShopBaseDTO;
import ecommerce.ecom.repository.ServicesShopRepository;

@Service
public class ServicesShopService {
    @Autowired
    private ServicesShopRepository servicesShopRepository;

    public ResponseEntity<List<ServicesShopBaseDTO>> getAllServices() {
        List<ServicesShop> servicesRaw = servicesShopRepository.findAll();
        List<ServicesShopBaseDTO> services = new ArrayList<ServicesShopBaseDTO>();
        for (ServicesShop service : servicesRaw){
            ServicesShopBaseDTO converted = ServicesShopBaseDTO.toDto(service);
            services.add(converted);
        }
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    public ResponseEntity<ServicesShopBaseDTO> getSingleService(int id) {
        Optional<ServicesShop> service = servicesShopRepository.findById(id);
        if(service.isPresent()){
            ServicesShopBaseDTO s = ServicesShopBaseDTO.toDto(service.get());
            return new ResponseEntity<>(s,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ServicesShopBaseDTO(), HttpStatus.NOT_FOUND);
        }
    }
}
