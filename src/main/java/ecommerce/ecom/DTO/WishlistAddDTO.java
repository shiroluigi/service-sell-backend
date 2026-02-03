package ecommerce.ecom.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WishlistAddDTO {
    private String user_id;
    private String service_id;
    private String email;
}
