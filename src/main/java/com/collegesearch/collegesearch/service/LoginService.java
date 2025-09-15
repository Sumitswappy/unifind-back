package com.collegesearch.collegesearch.service;

import com.collegesearch.collegesearch.Model.LoginModel;
import com.collegesearch.collegesearch.Util.SecurityManager;
import com.collegesearch.collegesearch.entity.UserEntity;
import com.collegesearch.collegesearch.entity.CollegeEntity;
import com.collegesearch.collegesearch.repository.CollegeRepository;
import com.collegesearch.collegesearch.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {
    UserService userService;
    UserRepository userRepository;
    SecurityManager securityManager;
    CollegeRepository collegeRepository;

    public Boolean checkLogin(LoginModel loginModel) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(loginModel.getUserName()).stream().findFirst();
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            return securityManager.checkPassword(loginModel.getPassword(), user.getPassword());
        } else {
            throw new IllegalStateException("User not found for email: " + loginModel.getUserName());
        }
    }
}