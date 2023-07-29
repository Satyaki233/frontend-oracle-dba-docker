package com.project.Backend.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Backend.POJO.Login;
import com.project.Backend.POJO.User;
import com.project.Backend.repository.BackendRepository;

@RestController
public class LoginController {
    @Autowired
    BackendRepository backendRepository;

    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Login login){
        try{

            if(backendRepository.validateUser(login.getEmail(), login.getPassword())){
                System.out.println("Login Successful");
                return new ResponseEntity<>("Login successful",HttpStatus.OK);
            }else{
                System.out.println("User Not Found ");
            }
        }catch(Exception e){
            System.out.println("Some error has occur");
        }
        
        return new ResponseEntity<>("user not found",HttpStatus.NOT_ACCEPTABLE);

    }

    @PutMapping("/login/update/{id}")
    public ResponseEntity<String> updatePassword(@PathVariable String id,@RequestBody Login login){
        login.setId(id);
        try{
            if(backendRepository.updatePassword(login)){
                System.out.println("Update user successfull");
                return new ResponseEntity<>("Update Succesful",HttpStatus.OK);
            }else{
                System.out.println("Update Not Succesful");
            }
            
        }catch(Exception e){
            System.out.println("Some error !");
        }
        return new ResponseEntity<>("Update Not Succesful , Check input !",HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/all/login")
    public ResponseEntity<List<Login>> getAllLogin() throws Exception{
        List<Login> allLogin =  backendRepository.getAllLogin();
        return new ResponseEntity<>(allLogin,HttpStatus.OK);
    }
}
