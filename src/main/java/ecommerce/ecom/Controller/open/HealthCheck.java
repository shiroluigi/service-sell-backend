package ecommerce.ecom.Controller.open;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.DTO.CommonDTO;

@RestController
@RequestMapping("/health")
public class HealthCheck {
    
    @GetMapping
    public ResponseEntity<?> getHealth(){
        return new ResponseEntity<>(new CommonDTO("HEALTH_CHECK","OK",null),HttpStatus.OK);
    }
}
