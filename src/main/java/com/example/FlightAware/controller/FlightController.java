package com.example.FlightAware.controller;

import com.example.FlightAware.model.User;
import com.example.FlightAware.model.UserIp;
import com.example.FlightAware.repository.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FlightController {

    @Autowired
    private UserRepo userRepo;

    CityCode code = new CityCode();

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
            return "Already exist";
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
                return "Incorrect password";
            }
        }
        else{
            return "User doesn't exist";
        }
    }

    ApiCalls apiCalls = new ApiCalls();
    @PostMapping("/flight-search")
    public String flight(@RequestBody UserIp userIp) throws IOException, ParseException, InterruptedException {
        Integer person;
        String origin, destination, departureDate, returnDate;

        person = userIp.getPersonCnt();
        origin = userIp.getOrigin();
        destination = userIp.getDest();
        departureDate = userIp.getDepartDate();
//        returnDate = userIp.getReturnDate();

        String oCode = code.codes(origin.toLowerCase());
        String dCode = code.codes(destination.toLowerCase());

//        System.out.println(oCode + " " + dCode);
//        System.out.println(person);
//        System.out.println(departureDate);
//        System.out.println(returnDate);

        String str = apiCalls.fprices(person, oCode, dCode, departureDate);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> restMap = objectMapper.readValue(str, Map.class);

        System.out.println(restMap.toString());

//        ArrayList<String> qlist=(ArrayList<String>)restMap.get("Quotes");
//        ArrayList<String> plist=(ArrayList<String>)restMap.get("Places");
//        ArrayList<String> clist=(ArrayList<String>)restMap.get("Carriers");
//        ArrayList<String> crlist=(ArrayList<String>)restMap.get("Currencies");
//        Iterator qit = qlist.iterator();
//        Iterable<Object> qarray = (Iterable<Object>) StreamSupport.stream(
//                Spliterators.spliteratorUnknownSize(qit, 0),false).collect(Collectors.toList());
//        Iterator pit=plist.iterator();
//        Iterable<Object> parray = (Iterable<Object>) StreamSupport.stream(
//                Spliterators.spliteratorUnknownSize(pit, 0),false).collect(Collectors.toList());
//        Iterator cit=clist.iterator();
//        Iterable<Object> carray = (Iterable<Object>) StreamSupport.stream(
//                Spliterators.spliteratorUnknownSize(cit, 0),false).collect(Collectors.toList());
//        Iterator crit=crlist.iterator();
//        Iterable<Object> crarray = (Iterable<Object>) StreamSupport.stream(
//                Spliterators.spliteratorUnknownSize(crit, 0),false).collect(Collectors.toList());

//        System.out.println("qaeear is "+qlist);

        return "Welcome";
    }
}
