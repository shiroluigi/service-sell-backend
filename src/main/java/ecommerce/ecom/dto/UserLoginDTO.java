package ecommerce.ecom.dto;

import ecommerce.ecom.Entities.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDTO extends UserBaseDTO{
    String errMsg;

    public static UserLoginDTO toDto(User user){
        UserLoginDTO converted = new UserLoginDTO();
        converted.setFirstName(user.getFirstName());
        converted.setLastName(user.getLastName());
        converted.setEmail(user.getEmail());
        converted.setId(user.getId());
        converted.setCountryCode(user.getCountryCode());
        converted.setPhone(user.getPhone());
        return converted;
    }

}
