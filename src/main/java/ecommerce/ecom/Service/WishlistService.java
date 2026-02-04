package ecommerce.ecom.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ecommerce.ecom.Common.ServicesShopCommonService;
import ecommerce.ecom.Common.UserCommonService;
import ecommerce.ecom.DTO.CommonDTO;
import ecommerce.ecom.DTO.ServicesShopBaseDTO;
import ecommerce.ecom.DTO.UserBaseDTO;
import ecommerce.ecom.DTO.WishlistAddDTO;
import ecommerce.ecom.DTO.WishlistDTO;
import ecommerce.ecom.Models.ServicesShop;
import ecommerce.ecom.Models.User;
import ecommerce.ecom.Models.Wishlist;
import ecommerce.ecom.Repository.WishlistRepository;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private ServicesShopCommonService servicesShopCommonService;
    @Autowired
    private UserCommonService userCommonService;

    public ResponseEntity<CommonDTO> addToWishlist(WishlistAddDTO wishDto) {
        try {
            ServicesShopBaseDTO serviceDto = new ServicesShopBaseDTO();
            serviceDto.setId(wishDto.getService_id());
            Optional<ServicesShop> serviceOptional = servicesShopCommonService.findService(serviceDto);
            UserBaseDTO userDto = new UserBaseDTO();
            userDto.setId(wishDto.getUser_id());
            userDto.setEmail(wishDto.getEmail());
            List<User> userList = userCommonService.findUser(userDto);
            if (serviceOptional.isPresent() && !userList.isEmpty()){
                Wishlist wishlist = new Wishlist();
                wishlist.setService(serviceOptional.get());
                wishlist.setUser(userList.get(0));
                wishlist.setTimestamp(LocalDateTime.now());
                wishlistRepository.save(wishlist);
                return new ResponseEntity<>(new CommonDTO("ADD WISHLIST","CREATED",""),HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(new CommonDTO("ADD WISHLIST","ERROR","User or service not found"),HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new CommonDTO("ADD WISHLIST","ERROR","Something went wrong.."),HttpStatus.INTERNAL_SERVER_ERROR);
        }   
    }

    public ResponseEntity<?> getAllWishlist(String userId) {
        try {
            List<Wishlist> wl_raw = wishlistRepository.findByUserId(userId);
            List<WishlistDTO> wishlistDto = new ArrayList<>();
            if (!wl_raw.isEmpty()){
                for(Wishlist wl : wl_raw){
                    WishlistDTO wldto = new WishlistDTO();
                    wldto.setId(wl.getId());
                    wldto.setService_id(String.valueOf(wl.getService().getId()));
                    wldto.setService_name(wl.getService().getService_name());
                    wldto.setService_price(String.valueOf(wl.getService().getPrice()));
                    wldto.setTimestamp(String.valueOf(wl.getTimestamp()));
                    wldto.setUser_name(wl.getUser().getFirstName() + " " + wl.getUser().getLastName());
                    wishlistDto.add(wldto);
                }
                return new ResponseEntity<>(wishlistDto,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new CommonDTO("GET WISHLIST","ERROR","No items found"),HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new CommonDTO("GET WISHLIST","ERROR","Something went wrong.."),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
