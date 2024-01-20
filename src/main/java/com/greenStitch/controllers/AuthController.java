package com.greenStitch.controllers;

import com.greenStitch.exceptions.UserAlreadyExistsException;
import com.greenStitch.models.JWTRequest;
import com.greenStitch.models.JWTResponse;
import com.greenStitch.security.JWTHelper;
import com.greenStitch.services.UserService;
import com.greenStitch.utils.EmailValidator;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.greenStitch.entities.User;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTHelper helper;

    @Autowired
    private UserService userService;


    @GetMapping
    public String hello(){
        return "Hello for unauthenticated";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JWTRequest user) {

        if (StringUtils.isBlank(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email is required.");
        }

        if (StringUtils.isBlank(user.getPassword())) {
            return ResponseEntity.badRequest().body("Password is required.");
        }
        user.setEmail(user.getEmail().trim());

        if (!(new EmailValidator().isValidEmail(user.getEmail()))) {
            return ResponseEntity.badRequest().body("Enter a valid email");
        }

        try {

            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            this.doAuthenticate(user.getEmail(), user.getPassword());
            String token = this.helper.generateToken(userDetails);

            JWTResponse response = JWTResponse.builder()
                    .jwtToken(token)
                    .username(userDetails.getUsername()).build();
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException | UsernameNotFoundException e) {
            System.out.println("Inside bade");
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.out.println("inside exception");
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (StringUtils.isBlank(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email is required.");
        }

        if (StringUtils.isBlank(user.getName())) {
            return ResponseEntity.badRequest().body("Name is required.");
        }

        if (StringUtils.isBlank(user.getPassword())) {
            return ResponseEntity.badRequest().body("Password is required.");
        }
        if (!(new EmailValidator().isValidEmail(user.getEmail()))) {
            return ResponseEntity.badRequest().body("Enter a valid email");
        }
        if (user.getPassword().contains(" ")) {
            return ResponseEntity.badRequest().body("Password should not contain white space");
        }

        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (UserAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    private void doAuthenticate(String email, String password) {
        System.out.println("do authenticate called---------");
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid Username or Password  !!");
        }

    }

//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<String> badCredentialsException() {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credentials Invalid");
//    }
//    @ExceptionHandler(UsernameNotFoundException.class)
//    public ResponseEntity<String> usernameNotFoundException() {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//    }

}
