package ecommerce.ecom.common;

import java.util.ArrayList;
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

    public List<User> findUser(UserBaseDTO user){
        List<User> res = new ArrayList<>();
        if (user.getId() != null){
            Optional<User> u = userRepository.findById(user.getId());
            if(u.isPresent()){
                res.add(u.get());
                return res;
            }
        }
        else if (user.getEmail() != null){
            return userRepository.findAllByEmail(user.getEmail());
        }
        return res;
    }
}
