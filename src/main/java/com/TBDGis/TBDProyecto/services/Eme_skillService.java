package com.TBDGis.TBDProyecto.services;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "eme_habs")
public class Eme_skillService {
    private final Eme_skillRepository eme_skillRepository;
    Eme_skillService(Eme_skillRepository eme_skillRepository){
        this.eme_skillRepository = eme_skillRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Eme_skill> getAllEme_habs() {
        return eme_skillRepository.getAllEme_habs();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countEmergencias(){
        int total = eme_skillRepository.countEme_habs();
        return String.format("Tienes %s emergencias_habilidades!!", total);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Eme_skill getEme_skillById(@PathVariable(value = "id") Integer id){
        return this.eme_skillRepository.getEme_skillById(id);
    }

    @PostMapping("/createEme_hab")
    @ResponseBody
    public Eme_skill createEmergencia(@RequestBody Eme_skill eme_skill){
        Eme_skill result = eme_skillRepository.createEme_hab(eme_skill);
        return result;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateEme_skill(@PathVariable(value = "id") int id, Eme_skill eme_skill) {
        eme_skillRepository.updateEme_skill(id, eme_skill);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteEme_skill(@PathVariable(value = "id") int id, Eme_skill eme_skill){
        eme_skillRepository.deleteEme_skill(id, eme_skill);
    }
}
