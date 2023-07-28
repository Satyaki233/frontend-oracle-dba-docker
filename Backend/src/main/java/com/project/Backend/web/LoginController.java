package com.project.Backend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Backend.POJO.Login;
import com.project.Backend.POJO.User;
import com.project.Backend.repository.BackendRepository;

@RestController
public class LoginController {
    @Autowired
    BackendRepository backendRepository;

    
    @PostMapping("/login")
    public ResponseEntity<HttpStatus> loginUser(@RequestBody Login login) throws Exception{

        

        if(backendRepository.validateUser(login.getEmail(), login.getPassword())){
            System.out.println("success");
        }else{
            System.out.println("Not succes");
        }

        return new ResponseEntity<HttpStatus>(HttpStatus.OK);


    }

    @GetMapping("/all/login")
    public ResponseEntity<List<Login>> getAllLogin() throws Exception{
        List<Login> allLogin =  backendRepository.getAllLogin();
        return new ResponseEntity<>(allLogin,HttpStatus.OK);
    }
}
