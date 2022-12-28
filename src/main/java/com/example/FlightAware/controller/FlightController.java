package com.example.FlightAware.controller;

import com.example.FlightAware.model.User;
import com.example.FlightAware.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FlightController {

    @Autowired
    private UserRepo userRepo;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @GetMapping("/")
    public String hello(){
        return "Hello From Dharmendra";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome To FlightAware Application";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user){
        User existingUser = userRepo.findByEmailIdIgnoreCase(user.getEmailId());

        if(existingUser == null){
            user.setPass(encoder.encode(user.getPass()));
            userRepo.save(user);
            return "Registered successfully";
        }
        else{
            return "EmailId already exist already exists!!!!";
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user){
        User existingUser = userRepo.findByEmailIdIgnoreCase(user.getEmailId());

        if(existingUser != null){
            boolean isPassMatch = encoder.matches(user.getPass(), existingUser.getPass());
            if(isPassMatch){
                return "Logged in Successfully";
            }
            else{
                return "Incorrect password, Try again.";
            }
        }
        else{
            return "User doesn't exist !! Register your self";
        }
    }
}
