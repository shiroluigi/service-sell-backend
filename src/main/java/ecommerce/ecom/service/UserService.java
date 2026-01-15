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
import ecommerce.ecom.enums.UserRoleEnum;
import ecommerce.ecom.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCommonService userCommonService;

    public ResponseEntity<CommonDTO> registerUser(UserBaseDTO newUser) {
        List<User> userList = userCommonService.findUser(newUser);
        if (!userList.isEmpty()) {
            return new ResponseEntity<>(new CommonDTO("User Registration", "fail", "Email already exists"),
                    HttpStatus.CONFLICT);
        } else {
            User u = UserBaseDTO.toUser(newUser);
            u.setRole(UserRoleEnum.REGULAR_USER);
            userRepository.save(u);
            return new ResponseEntity<>(new CommonDTO("User Registration", "SUCCESS", ""), HttpStatus.CREATED);
        }
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

}
