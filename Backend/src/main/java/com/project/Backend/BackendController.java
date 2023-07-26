package com.project.Backend;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class BackendController {

    List<User> users = new ArrayList<>();
    
    @GetMapping("/all")
    public ResponseEntity<List<User>> getUser(){
        
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<HttpStatus> showUser(@RequestBody User user){
        System.out.println(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
