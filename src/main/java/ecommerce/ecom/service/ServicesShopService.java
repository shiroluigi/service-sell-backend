package ecommerce.ecom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ecommerce.ecom.Entities.ServicesShop;
import ecommerce.ecom.dto.CommonDTO;
import ecommerce.ecom.dto.ServicesShopBaseDTO;
import ecommerce.ecom.repository.ServicesShopRepository;

@Service
public class ServicesShopService {
    @Autowired
    private ServicesShopRepository servicesShopRepository;

    public ResponseEntity<List<ServicesShopBaseDTO>> getAllServices() {
        List<ServicesShop> servicesRaw = servicesShopRepository.findAll();
        List<ServicesShopBaseDTO> services = new ArrayList<ServicesShopBaseDTO>();
        for (ServicesShop service : servicesRaw) {
            ServicesShopBaseDTO converted = ServicesShopBaseDTO.toDto(service);
            services.add(converted);
        }
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    public ResponseEntity<ServicesShopBaseDTO> getSingleService(int id) {
        Optional<ServicesShop> service = servicesShopRepository.findById(id);
        if (service.isPresent()) {
            ServicesShopBaseDTO s = ServicesShopBaseDTO.toDto(service.get());
            return new ResponseEntity<>(s, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ServicesShopBaseDTO(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteService(ServicesShopBaseDTO serviceDto) {
        try {
            Optional<ServicesShop> serviceOptional = servicesShopRepository
                    .findById(Integer.parseInt(serviceDto.getId()));
            if (serviceOptional.isPresent()) {
                servicesShopRepository.delete(serviceOptional.get());
                return new ResponseEntity<>(new CommonDTO("Delete Service", "DELETED", null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new CommonDTO("Delete Service", "404", "Service not found"),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new CommonDTO("Delete Service", "500", "Some error occured"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> addService(ServicesShopBaseDTO serviceDto) {
        try {
            ServicesShop service = ServicesShopBaseDTO.toServicesShop(serviceDto);
            servicesShopRepository.save(service);
            return new ResponseEntity<>(new CommonDTO("Add service", "SUCCESS", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CommonDTO("Delete Service", "500", "Some error occured"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> editService(ServicesShopBaseDTO serviceDto) {
        try {
            Optional<ServicesShop> serviceOptional = servicesShopRepository
                    .findById(Integer.parseInt(serviceDto.getId()));
            if (serviceOptional.isPresent()) {
                if (serviceOptional.isPresent() && serviceDto != null) {
                    ServicesShop service = serviceOptional.get();
                    if (serviceDto.getCurrency() != null && !serviceDto.getCurrency().isEmpty()) {
                        service.setCurrency(serviceDto.getCurrency());
                    }
                    if (serviceDto.getDescription() != null && !serviceDto.getDescription().isEmpty()) {
                        service.setDescription(serviceDto.getDescription());
                    }
                    if (serviceDto.getDuration() != null && !serviceDto.getDuration().isEmpty()) {
                        service.setDuration(serviceDto.getDuration());
                    }
                    if (serviceDto.getService_name() != null && !serviceDto.getService_name().isEmpty()) {
                        service.setService_name(serviceDto.getService_name());
                    }
                    if (serviceDto.getPrice() != null && !serviceDto.getPrice().isEmpty()) {
                        service.setPrice(Integer.parseInt(serviceDto.getPrice()));
                    }
                    servicesShopRepository.save(service);
                }
                return new ResponseEntity<>(new CommonDTO("Edit Service", "EDITED", null), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new CommonDTO("Edit Service", "404", "Service not found"),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new CommonDTO("Edit Service", "500", "Some error occured"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
