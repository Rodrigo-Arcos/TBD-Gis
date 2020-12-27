package com.TBDGis.TBDProyecto.services;

import com.TBDGis.TBDProyecto.models.Emergency_Skill;
import com.TBDGis.TBDProyecto.repositories.Emergency_SkillRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "eme_habs")
public class Emergency_SkillService {
    private final Emergency_SkillRepository emergency_skillRepository;
    Emergency_SkillService(Emergency_SkillRepository emergency_skillRepository){
        this.emergency_skillRepository = emergency_skillRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Emergency_Skill> getAllEme_habs() {
        return emergency_skillRepository.getAllEme_habs();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countEmergencias(){
        int total = emergency_skillRepository.countEme_habs();
        return String.format("Tienes %s emergencias_habilidades!!", total);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Emergency_Skill getEme_SkillById(@PathVariable(value = "id") Integer id){
        return this.emergency_skillRepository.getEme_SkillById(id);
    }

    @PostMapping("/createEme_hab")
    @ResponseBody
    public Emergency_Skill createEme_Skill(@RequestBody Emergency_Skill eme_skill){
        Emergency_Skill result = emergency_skillRepository.createEme_Skill(eme_skill);
        return result;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateEme_Skill(@PathVariable(value = "id") int id, Emergency_Skill eme_skill) {
        emergency_skillRepository.updateEme_skill(id, eme_skill);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteEme_Skill(@PathVariable(value = "id") int id, Emergency_Skill eme_skill){
        emergency_skillRepository.deleteEme_Skill(id, eme_skill);
    }
}
