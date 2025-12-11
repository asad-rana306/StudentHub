package com.Student.Hub.Controller;

import com.Student.Hub.Entity.User;
import com.Student.Hub.Services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userServices.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            userServices.deleteById(objectId);
            return ResponseEntity.status(200).body("Deleted Successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid ID format");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateUserById(
            @PathVariable String id,
            @RequestBody User updatedUser
    ) {
        try {
            ObjectId objectId = new ObjectId(id);
            User user = userServices.updateById(objectId, updatedUser);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid ID format");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
