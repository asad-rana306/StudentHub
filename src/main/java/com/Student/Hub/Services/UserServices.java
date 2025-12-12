package com.Student.Hub.Services;

import com.Student.Hub.Entity.User;
import com.Student.Hub.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    private final UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User addUser(User user) {
        if(userRepository.findByUserName(user.getUserName()) != null){
            throw new RuntimeException("This ID is already taken, please choose another.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public void deleteById(ObjectId id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
    public User updateById(ObjectId id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    if (updatedUser.getUserName() != null) {
                        existingUser.setUserName(updatedUser.getUserName());
                    }
                    if (updatedUser.getEmail() != null) {
                        existingUser.setEmail(updatedUser.getEmail());
                    }
                    if (updatedUser.getPassword() != null) {
                        existingUser.setPassword(updatedUser.getPassword());
                    }
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}
