package ecommerce.ecom.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistDTO {
    private String id;
    private String user_name;
    private String service_name;
    private String service_id;
    private String service_price;
    private String timestamp;
}
