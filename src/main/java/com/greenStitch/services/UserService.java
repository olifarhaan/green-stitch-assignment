package com.greenStitch.services;

import com.greenStitch.entities.User;
import com.greenStitch.exceptions.UserAlreadyExistsException;
import com.greenStitch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    private final List<User> store=new ArrayList<>();

//    public UserService(){
//        store.add(new User(UUID.randomUUID().toString(), "Ali", "alifarhan23107@gmail.com"));
//        store.add(new User(UUID.randomUUID().toString(), "Rashmi", "rashmi@gmail.com"));
//        store.add(new User(UUID.randomUUID().toString(), "Nishtha", "nishtha@gmail.com"));
//        store.add(new User(UUID.randomUUID().toString(), "Aryan", "aryan@gmail.com"));
//    }

//    public List<User> getUsers(){
//        return this.store;
//    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){

        if(userRepository.findByEmail(user.getEmail()).isPresent()) throw new UserAlreadyExistsException("User already exists with this email");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try{
            return userRepository.save(user);
        }
        catch (Exception e){
            throw new RuntimeException("Something wrong happened with the database");
        }

    }

}
