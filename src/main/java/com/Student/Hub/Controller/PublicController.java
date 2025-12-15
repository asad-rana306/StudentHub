package com.Student.Hub.Controller;

import com.Student.Hub.Entity.User;
import com.Student.Hub.Services.UserServices;
import com.Student.Hub.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final JwtUtil jwtUtil;
    private final UserServices userServices;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public PublicController(JwtUtil jwtUtil, UserServices userServices,
                            AuthenticationManager authenticationManager,
                            UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userServices = userServices;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    // ------------------- SIGNUP -------------------
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        User savedUser = userServices.addUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
            );
            System.out.println("password authenticated");
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());

            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            System.out.println(userDetails.getUsername());
            return ResponseEntity.ok(jwt);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Incorrect userId or password");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something went wrong: " + e.getMessage());
        }
    }

}
