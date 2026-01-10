package ecommerce.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ecommerce.ecom.Entities.User;
import ecommerce.ecom.common.UserCommonService;
import ecommerce.ecom.dto.CommonDTO;
import ecommerce.ecom.dto.UserBaseDTO;
import ecommerce.ecom.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserCommonService userCommonService;

    public ResponseEntity<CommonDTO> registerUser(UserBaseDTO newUser){
        List<User> userList = userCommonService.findUser(newUser);
        if(!userList.isEmpty()){
            return new ResponseEntity<>(new CommonDTO("User Registration","fail","Email already exists"),HttpStatus.CONFLICT); 
        }else{
            User u = UserBaseDTO.toUser(newUser);
            userRepository.save(u);
            return new ResponseEntity<>(new CommonDTO("User Registration","SUCCESS",""),HttpStatus.CREATED); 
        }
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public ResponseEntity<UserBaseDTO> login(UserBaseDTO loginUser) {
        List<User> userList = userCommonService.findUser(loginUser);
        if (!userList.isEmpty()){
            User user = userList.get(0);
            UserBaseDTO userLoginDto = UserBaseDTO.toDto(user);
            return new ResponseEntity<>(userLoginDto, HttpStatus.OK);
        }else{       
            UserBaseDTO user = new UserBaseDTO();
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
        }
    }
}
