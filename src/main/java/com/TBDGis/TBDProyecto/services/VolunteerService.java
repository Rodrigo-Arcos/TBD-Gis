package com.TBDGis.TBDProyecto.services;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "volunteer")
public class VolunteerService {
    private final VolunteerRepository voluntarioRepository;
    VolunteerService(VolunteerRepository volunteerRepository){
        this.volunteerRepository = volunteerRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Volunteer> getAllVolunteers(){
        return this.volunteerRepository.getAllVolunteers();
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Volunteer getVolunteerById(@PathVariable(value = "id") Integer id){
        return this.volunteerRepository.getVolunteerById(id);
    }

    @PostMapping("/createVolunteer")
    @ResponseBody
    public Volunteer createVolunteer(@RequestBody Volunteer v){
        Volunteer newVolunteer = volunteerRepository.createVolunteer(v);
        return newVolunteer;
    }
    /*
    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateVolunteer(@PathVariable(value = "id") int id, Volunteer volunteer) {
        volunteerRepository.updateVolunteer(id, volunteer);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteVolunteer(@PathVariable(value = "id") int id, Volunteer volunteer){
        volunteerRepository.deleteVolunteer(id, volunteer);
    }
    */
}
