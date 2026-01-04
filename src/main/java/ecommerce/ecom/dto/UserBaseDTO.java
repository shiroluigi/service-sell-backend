package ecommerce.ecom.dto;

import ecommerce.ecom.Entities.User;
import ecommerce.ecom.common.Cryptography;
import lombok.Data;

@Data
public class UserBaseDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    public static User toUser(UserBaseDTO user){
        User converted = new User();
        converted.setFirstName(user.getFirstName());
        converted.setLastName(user.getLastName());
        converted.setEmail(user.getEmail());
        converted.setPassword(Cryptography.encryptTobCrypt(user.getPassword()));
        return converted;
    }
}
