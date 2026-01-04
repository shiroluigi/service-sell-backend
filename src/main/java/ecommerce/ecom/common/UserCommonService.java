package ecommerce.ecom.common;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ecommerce.ecom.Entities.User;
import ecommerce.ecom.dto.UserBaseDTO;
import ecommerce.ecom.repository.UserRepository;

@Component
public class UserCommonService {
    @Autowired
    private UserRepository userRepository;

    public Optional<List<User>> findUser(UserBaseDTO user){
        Optional<List<User>> res = null;
        // Add other conditions, can search with first name or last name also
        if (user.getEmail() != null){
            res = userRepository.findAllByEmail(user.getEmail());
        }
        return res;
    }

}
