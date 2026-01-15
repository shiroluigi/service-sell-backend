package ecommerce.ecom.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ecommerce.ecom.Entities.User;
import ecommerce.ecom.Entities.UserPrincipal;
import ecommerce.ecom.dto.UserBaseDTO;
import ecommerce.ecom.repository.UserRepository;

@Service
public class UserCommonService implements UserDetailsService {
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
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserBaseDTO ub = new UserBaseDTO();
        ub.setEmail(email);
        List<User> user = findUser(ub);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Email " + email + " not found");
        }
        return new UserPrincipal(user.get(0));
    }
}
