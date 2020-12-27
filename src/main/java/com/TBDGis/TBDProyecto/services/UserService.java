package com.TBDGis.TBDProyecto.services;

import com.TBDGis.TBDProyecto.models.User;
import com.TBDGis.TBDProyecto.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "user")
public class UserService {

    public final UserRepository userRepository;
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<User> getAllUser(){
        return this.userRepository.getAllUser();
    }

    @PostMapping("/createUser")
    @ResponseBody
    public User createUser(@RequestBody User v){
        User newUSer = userRepository.createUser(v);
        return newUSer;
    }

    @PostMapping("/logIn")
    @ResponseBody
    public String logIn(@RequestBody User v){
        String answer= userRepository.logIn(v);
        return answer;
    }
    @PostMapping("/logOut")
    @ResponseBody
    public String logOut(@RequestBody User v){
        String answer= userRepository.logOut(v);
        return answer;
    }
    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable(value = "id") int id, User user1){
        userRepository.deleteUser(id, user1);
    }

}
