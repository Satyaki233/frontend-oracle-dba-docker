package com.project.Backend.web;

import org.springframework.web.bind.annotation.RestController;

import com.project.Backend.POJO.User;
import com.project.Backend.conn.DatabaseConnection;
import com.project.Backend.execption.NoUserException;
import com.project.Backend.repository.BackendRepository;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class RegisterController {

    // @Autowired
    // RegisterService registerService ;

    @Autowired
    BackendRepository backendRepository;    



    //List<User> users = new ArrayList<>();
    
    // @GetMapping("/all")
    // public ResponseEntity<List<User>> getUser(){
        
    //     return new ResponseEntity<>(users,HttpStatus.OK);
    // }


    // @GetMapping("/all")
    // public ResponseEntity<List<User>> getUsers(){
    //     List<User> users;
    //     try {
    //         System.out.print(DatabaseConnection.getConnection());
    //         users = registerService.getUsers();
    //     } catch (Exception e) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    //     return new ResponseEntity<>(users,HttpStatus.OK);
    // }


    @PostMapping("/user")
    public ResponseEntity<HttpStatus> showUser(@RequestBody User user) throws Exception{
        boolean result = backendRepository.insertUser(user.getId(), user.getName(), user.getPhone(), user.getPassword(), user.getEmail(), user.getAddress());
        if(result){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    
    }

    // @GetMapping("/user/{id}")
    // public ResponseEntity<User> getUser(@PathVariable String id) {
    //     try{
    //         User user =registerService.getUserById(id);
    //         return new ResponseEntity<>(user, HttpStatus.OK);
            
    //     }catch(NoUserException e){
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }

    // @PutMapping("/user/{id}")
    // public ResponseEntity<HttpStatus> updateUser(@PathVariable String id , @RequestBody User user){
    //    try{ 
    //     int index = registerService.findIndexById(id);
        
    //     registerService.updateUser(index, user);
    //     return new ResponseEntity<>(HttpStatus.ACCEPTED);
    // }catch(NoUserException e){
    //                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    //     }
    // }

    // @DeleteMapping("/user/{id}")
    // public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id ){
    //     try{ 
    //         int index = registerService.findIndexById(id);
            
    //         registerService.deleteUser(index);
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     }catch(NoUserException e){
    //                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    //     }

    // }
}
