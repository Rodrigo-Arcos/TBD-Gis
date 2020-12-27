package com.TBDGis.TBDProyecto.services;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(value = "skills")
public class SkillService {
    private final SkillRepository skillRepository;
    SkillService(SkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Skill> getAllSkills() {
        return skillRepository.getAllSkilles();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countSkills(){
        int total = skillRepository.countSkilles();
        return String.format("Tienes %s Habilidades!!", total);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Skill getSkillById(@PathVariable(value = "id") Integer id){
        return this.skillRepository.getSkillById(id);
    }

    @PostMapping("createSkill")
    @ResponseBody
    public Skill createSkill(@RequestBody Skill skill){
        Skill result = skillRepository.createSkill(skill);
        return result;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateSkill(@PathVariable(value = "id") int id, Skill skill) {
        skillRepository.updateSkill(id, skill);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteSkill(@PathVariable(value = "id") int id, Skill skill){
        skillRepository.updateSkill(id, skill);
    }
}
