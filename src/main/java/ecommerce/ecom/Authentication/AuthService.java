package ecommerce.ecom.Authentication;

import org.springframework.security.core.Authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import ecommerce.ecom.Common.UserCommonService;
import ecommerce.ecom.DTO.CommonDTO;
import ecommerce.ecom.DTO.JwtDto;
import ecommerce.ecom.DTO.UserBaseDTO;
import ecommerce.ecom.Models.User;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserCommonService userCommonService;

    public ResponseEntity<?> login(UserBaseDTO loginUser) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
            if (auth.isAuthenticated()) {
                UserBaseDTO u = new UserBaseDTO();
                u.setEmail(auth.getName());
                List<User> users = userCommonService.findUser(u);
                if(users.isEmpty()){
                    throw new Exception("User Not Found");
                }
                u = UserBaseDTO.toDto(users.get(0));
                JwtDto jwtObj = new JwtDto(u, jwtService.generateToken(u.getEmail()));
                return new ResponseEntity<>(jwtObj, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new CommonDTO("LOGIN", "ERROR", "Username or password incorrect"),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new CommonDTO("LOGIN", "ERROR", e.getMessage()),
                    HttpStatus.NOT_FOUND);
        }
    }
}
