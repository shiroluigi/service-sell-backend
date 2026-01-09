package ecommerce.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.dto.ServicesShopResponseDTO;
import ecommerce.ecom.service.ServicesShopService;

@RestController
@RequestMapping("/services")
public class ServicesShopController {
    @Autowired
    private ServicesShopService servicesShopService;

    @GetMapping("/all")
    public List<ServicesShopResponseDTO> getAllServices(){
        return servicesShopService.getAllServices();
    }
    @GetMapping("/single")
    public ServicesShopResponseDTO getSingleService(@RequestParam int id){
        return servicesShopService.getSingleService(id);
    }
    
}
