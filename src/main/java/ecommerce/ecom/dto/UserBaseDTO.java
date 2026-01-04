package ecommerce.ecom.dto;

import lombok.Data;

@Data
public class UserBaseDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
