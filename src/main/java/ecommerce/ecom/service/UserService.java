package ecommerce.ecom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecommerce.ecom.Entities.User;
import ecommerce.ecom.common.UserCommonService;
import ecommerce.ecom.dto.CommonDTO;
import ecommerce.ecom.dto.UserBaseDTO;
import ecommerce.ecom.dto.UserLoginDTO;
import ecommerce.ecom.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserCommonService userCommonService;

    public CommonDTO registerUser(UserBaseDTO newUser){
        Optional<List<User>> userOptionalList = userCommonService.findUser(newUser);
        if(userOptionalList.isPresent() && !userOptionalList.get().isEmpty()){
            return new CommonDTO("User Registration","fail","Email already exists");
        }else{
            User u = UserBaseDTO.toUser(newUser);
            userRepository.save(u);
            return new CommonDTO("User Registration","success",null);
        }
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public UserLoginDTO login(UserBaseDTO loginUser) {
        Optional<List<User>> userOptionalList = userCommonService.findUser(loginUser);
        if (userOptionalList.isPresent() && !userOptionalList.get().isEmpty()){
            User user = userOptionalList.get().get(0); // FIX
            UserLoginDTO userLoginDto = UserLoginDTO.toDto(user);
            return userLoginDto;
        }else{       
            UserLoginDTO user = new UserLoginDTO();
            user.setErrMsg("User login not found");
            return user;
        }
    }
}
