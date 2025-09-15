package com.collegesearch.collegesearch.controller;

import com.collegesearch.collegesearch.entity.ReviewsEntity;
import com.collegesearch.collegesearch.entity.UserEntity;
import com.collegesearch.collegesearch.repository.ReviewsRepository;
import com.collegesearch.collegesearch.repository.UserRepository;
import com.collegesearch.collegesearch.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("user")
@CrossOrigin(maxAge = 500)
public class UserController {
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewsRepository reviewsRepository;
    @PostMapping("add")
    public void addUser(@RequestBody UserEntity user){
        userService.addUser(user);
    }

    @GetMapping("get")
    public List<UserEntity> getUser(){
       return userService.getUser();
    }

    @GetMapping("get/{id}")
    public UserEntity getUserById(@PathVariable int id){
        return userService.getUserById(id)
                .orElseThrow(() -> {
                    throw new RuntimeException("User not found");
                });

    }
    @GetMapping("getByEmail")
    public List<UserEntity> getUserByEmail(@RequestParam("email") String email) {
        return userService.getUserByEmail(email);
    }
    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable int id){
        // Check if the user has associated reviews
        List<ReviewsEntity> userReviews = reviewsRepository.findByUseridId(id);

        if (!userReviews.isEmpty()) {
            // Delete all reviews associated with the user
            reviewsRepository.deleteAll(userReviews);
        }
        userService.deleteUser(id);
    }

    @PutMapping("update/{id}")
    public void updateUser(@PathVariable int id, @RequestBody UserEntity user){
        userService.updateUser(id,user);
    }
    @PutMapping("/update/photo/{id}")
    public ResponseEntity<UserEntity> updateUserProfilePhoto(@PathVariable int id, @RequestBody UserEntity updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setProfilephotoUri(updatedUser.getProfilephotoUri());
                    user.setPhotofile(updatedUser.getPhotofile());
                    return ResponseEntity.ok(userRepository.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
