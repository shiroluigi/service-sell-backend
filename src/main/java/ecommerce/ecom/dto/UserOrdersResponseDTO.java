package ecommerce.ecom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOrdersResponseDTO extends UserOrdersBaseDTO {
    private String errMsg;
}
