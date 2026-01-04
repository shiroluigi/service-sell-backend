package ecommerce.ecom.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Cryptography {
    public static String encryptTobCrypt(String text){
        return (new BCryptPasswordEncoder()).encode(text);
    }
}
