package ecommerce.ecom.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDto {
    private UserBaseDTO user;
    private String jwt;

    public JwtDto(UserBaseDTO u,String jwt){
        this.user = u;
        this.jwt = jwt;
    }
}
