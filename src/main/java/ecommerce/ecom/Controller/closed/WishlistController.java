package ecommerce.ecom.Controller.closed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ecommerce.ecom.DTO.CommonDTO;
import ecommerce.ecom.DTO.WishlistAddDTO;
import ecommerce.ecom.Service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity<CommonDTO> addToWishlist(@RequestBody WishlistAddDTO wish){
        return wishlistService.addToWishlist(wish);
    } 

    @GetMapping("/all")
    public ResponseEntity<?> getAllWishlist(@RequestParam String userId){
        return wishlistService.getAllWishlist(userId);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeWish(@RequestParam String wishId){
        return wishlistService.removeWish(wishId);
    }
}
