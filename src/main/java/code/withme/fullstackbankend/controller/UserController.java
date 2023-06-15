package code.withme.fullstackbankend.controller;


import code.withme.fullstackbankend.exception.UserNotFoundException;
import code.withme.fullstackbankend.model.User;
import code.withme.fullstackbankend.repsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/users")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }
    @GetMapping("/users")
    List<User> getAllUser(){
        return  userRepository.findAll();
    }
    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id){
        return  userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }
    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){
        return  userRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(()-> new UserNotFoundException(id));
    }
    @DeleteMapping("/users/{id}")
    String deleteUser (@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id "+id+" has been deleted success";
    }
}
