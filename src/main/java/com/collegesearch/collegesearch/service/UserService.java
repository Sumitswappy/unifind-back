package com.collegesearch.collegesearch.service;

import com.collegesearch.collegesearch.Util.SecurityManager;
import com.collegesearch.collegesearch.entity.UserEntity;
import com.collegesearch.collegesearch.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private SecurityManager securityManager;
    public void addUser(UserEntity user){
        user.setPassword(securityManager.encryptPassword(user.getPassword()));
        userRepository.save(user);
    }
    public List<UserEntity> getUser(){
        List<UserEntity> usrdet=new ArrayList<UserEntity>(userRepository.findAll());
        return usrdet;
    }
    public List<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<UserEntity> getUserById(int id) {
        return userRepository.findById(id);
    }
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
    public void updateUser(int id, UserEntity updatedUser) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity existingUser = optionalUser.get();
            // Update non-password fields
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setCity(updatedUser.getCity());
            existingUser.setState(updatedUser.getState());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setPhotofile(updatedUser.getPhotofile());
            existingUser.setProfilephotoUri(updatedUser.getProfilephotoUri());
            existingUser.setIsAdmin(updatedUser.getIsAdmin());
            existingUser.setIsCollegeUser(updatedUser.getIsCollegeUser());

            // Check if a new password is provided
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                // Encrypt and set the new password
                existingUser.setPassword(securityManager.encryptPassword(updatedUser.getPassword()));
            }
            if (updatedUser.getProfilephotoUri() != null && !updatedUser.getProfilephotoUri().isEmpty()) {

                existingUser.setProfilephotoUri(updatedUser.getProfilephotoUri());
            }
            if (updatedUser.getPhotofile() != null && !updatedUser.getPhotofile().isEmpty()) {

                existingUser.setPhotofile(updatedUser.getPhotofile());
            }

            // Save the updated user
            userRepository.save(existingUser);
        }
    }


}
