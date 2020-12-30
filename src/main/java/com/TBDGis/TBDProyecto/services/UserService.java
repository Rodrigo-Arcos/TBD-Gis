package com.TBDGis.TBDProyecto.services;

import com.TBDGis.TBDProyecto.models.Task_Skill;
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

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateUser(@PathVariable(value = "id") int id, User user){
        userRepository.updateUser(id, user);
    }

    @PostMapping("/logIn")
    @ResponseBody
    public User logIn(@RequestBody User v){
        User answer= userRepository.logIn(v);
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
