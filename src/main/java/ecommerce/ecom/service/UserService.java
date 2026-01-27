package ecommerce.ecom.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ecommerce.ecom.Entities.User;
import ecommerce.ecom.common.EmailService;
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
    @Autowired
    private EmailService emailService;

    public ResponseEntity<CommonDTO> registerUser(UserBaseDTO newUser) {
        List<User> userList = userCommonService.findUser(newUser);
        if (!userList.isEmpty()) {
            return new ResponseEntity<>(new CommonDTO("User Registration", "fail", "Email already exists"),
                    HttpStatus.CONFLICT);
        } else {
            User u = UserBaseDTO.toUser(newUser);
            u.setRole(UserRoleEnum.REGULAR_USER);
            userRepository.save(u);
            // Send email to user about registration
            emailService.sendEmail(newUser.getEmail(), "Registration Success for Service Sell.",
                    "Thank you " + newUser.getFirstName()
                            + " for successfully registering with us. \n Your account email is " + newUser.getEmail());
            return new ResponseEntity<>(new CommonDTO("User Registration", "SUCCESS", ""), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> addUserAdmin(UserBaseDTO newUser) {
        List<User> userList = userCommonService.findUser(newUser);
        if (!userList.isEmpty()) {
            return new ResponseEntity<>(new CommonDTO("User Registration", "fail", "Email already exists"),
                    HttpStatus.CONFLICT);
        } else {
            User u = UserBaseDTO.toUser(newUser);
            u.setRole(UserRoleEnum.valueOf(newUser.getRole()));
            userRepository.save(u);
            // Send email to user about registration
            emailService.sendEmail(newUser.getEmail(), "Registration Success for Service Sell.",
                    "Thank you " + newUser.getFirstName()
                            + " for successfully registering with us. \n Your account email is " + newUser.getEmail());
            return new ResponseEntity<>(new CommonDTO("User Registration", "SUCCESS", ""), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> deleteUserAdmin(UserBaseDTO newUser) {
        List<User> userList = userCommonService.findUser(newUser);
        if (userList.isEmpty()) {
            return new ResponseEntity<>(new CommonDTO("User Deletion", "fail", "Email not found"),
                    HttpStatus.NOT_FOUND);
        } else {
            User u = userList.get(0);
            userRepository.delete(u);
            // Send email to user about registration
            emailService.sendEmail(newUser.getEmail(), "Account permanently deleted", "\nYour account email " + newUser.getEmail()+ " is DELETED permanently.\n If this is a mistake please contact support.");
            return new ResponseEntity<>(new CommonDTO("User deletion", "SUCCESS", ""), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getRoles() {
        return new ResponseEntity<>(Arrays.asList(UserRoleEnum.values()),HttpStatus.OK);
    }
}
