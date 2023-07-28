// package com.project.Backend.service;

// import java.util.List;
// import java.util.stream.IntStream;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.project.Backend.POJO.User;
// import com.project.Backend.execption.NoUserException;
// import com.project.Backend.repository.BackendRepository;

// @Service
// public class RegisterService {
    
//     @Autowired
//     BackendRepository backendRepository;
    

//     public List<User> getUsers() throws NoUserException{
//         return backendRepository.getUsers();
//     } 

//     public void addUser(User user) {
//         backendRepository.addUser(user);
//     }

//     public void updateUser(int index , User user) throws NoUserException{
//         backendRepository.updateUser(index, user);
//     }

//     public void deleteUser(int index) throws NoUserException{
//         backendRepository.deleteUser(index);
//     }

//     public User getUserById(String id) throws NoUserException{
//        int index= findIndexById(id);
//        return backendRepository.getUser(index);
//     }

//     public int findIndexById(String id) throws NoUserException{
//         return IntStream.range(0, backendRepository.getUsers().size())
//             .filter(index -> backendRepository.getUsers().get(index).getId().equals(id))
//             .findFirst()
//             .orElseThrow();
//     }
// }
