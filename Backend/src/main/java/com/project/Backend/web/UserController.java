package com.project.Backend.web;

import org.springframework.web.bind.annotation.RestController;

import com.project.Backend.POJO.User;
import com.project.Backend.repository.BackendRepository;

import java.util.Random;

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
public class UserController {

    // @Autowired
    // RegisterService registerService ;

    @Autowired
    BackendRepository backendRepository;
    
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        User user= new User();
        try {
            user = backendRepository.getUserById(id);
            return new ResponseEntity<>(user,HttpStatus.OK);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
            System.out.println("some error !");
        }
        return new ResponseEntity<>(user,HttpStatus.BAD_REQUEST);

    }



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
    public ResponseEntity<String> addUser(@RequestBody User user){
        Random random = new Random();
        int x = random.nextInt(900) + 100;
        user.setId(Integer.toString(x));

        try{

            boolean result = backendRepository.insertUser(
                user.getId(),
                user.getName(), 
                user.getPhone(), 
                user.getPassword(), 
                user.getEmail(), 
                user.getAddress()
            );
            if(result){
                return new ResponseEntity<>("User registered",HttpStatus.ACCEPTED);
            }
        }catch(Exception e){
            return new ResponseEntity<>("Not valid input",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User not registered",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        try{
            if(backendRepository.deleteUser(id)){
                System.out.println(id + " User deleted ");
                return new ResponseEntity<>("User deleted",HttpStatus.OK);

            }
        }catch(Exception e){
            System.out.println("Some exception");
        }

        return new ResponseEntity<>("User not deleted , check input !",HttpStatus.BAD_REQUEST);

    }

    


}
