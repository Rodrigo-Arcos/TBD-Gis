package com.TBDGis.TBDProyecto.services;


import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "emergencies")
public class EmergencyService {

    private final EmergencyRepository emergencyRepository;
    EmergencyService(EmergencyRepository emergencyRepository){
        this.emergencyRepository = emergencyRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Emergency> getAllEmergencies() {
        return emergencyRepository.getAllEmergencies();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countEmergencies(){
        int total = EmergencyRepository.countEmergencies();
        return String.format("Tienes %s emergencias!!", total);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Emergency getEmergencyById(@PathVariable(value = "id") Integer id){
        return this.emergencieRepository.getEmergencyById(id);
    }

    @PostMapping("/createEmergency")
    @ResponseBody
    public Emergency createEmergency(@RequestBody Emergency emergency){
        Emergency result = emergencyRepository.createEmergency(emergency);
        return result;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateEmergency(@PathVariable(value = "id") int id, Emergency emergency) {
        emergencyRepository.updateEmergency(id, emergency);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteEmergency(@PathVariable(value = "id") int id, Emergency emergency){
        emergencyRepository.deleteEmergency(id, emergency);
    }
}
