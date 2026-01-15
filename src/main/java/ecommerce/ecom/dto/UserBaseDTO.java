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
    private String phone;
    private String countryCode;
    private String role;

    public static User toUser(UserBaseDTO user){
        User converted = new User();
        converted.setFirstName(user.getFirstName());
        converted.setLastName(user.getLastName());
        converted.setEmail(user.getEmail());
        converted.setPassword(Cryptography.encryptTobCrypt(user.getPassword()));
        converted.setPhone(user.getPhone());
        converted.setCountryCode(user.getCountryCode());
        return converted;
    }
    public static UserBaseDTO toDto(User user){
        UserBaseDTO converted = new UserBaseDTO();
        converted.setFirstName(user.getFirstName());
        converted.setLastName(user.getLastName());
        converted.setEmail(user.getEmail());
        converted.setPhone(user.getPhone());
        converted.setCountryCode(user.getCountryCode());
        converted.setId(user.getId());
        converted.setRole(""+user.getRole());
        return converted;
    }
}
