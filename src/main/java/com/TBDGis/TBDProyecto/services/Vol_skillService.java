package com.TBDGis.TBDProyecto.services;

import com.TBDGis.TBDProyecto.models.Skill;
import com.TBDGis.TBDProyecto.models.Volunteer;
import com.TBDGis.TBDProyecto.models.Volunteer_Skill;
import com.TBDGis.TBDProyecto.repositories.Volunteer_SkillRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "vol_skill")
public class Vol_skillService {
    private final Volunteer_SkillRepository vsRepo;
    Vol_skillService(Volunteer_SkillRepository vsRepo){
        this.vsRepo = vsRepo;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Volunteer_Skill> getAllVolSkill(){
        return this.vsRepo.getAllVol_Skill();
    }

    @PostMapping("createVolHab")
    @ResponseBody
    public Volunteer_Skill createVol_Skill(@RequestBody Volunteer v, Skill h, Volunteer_Skill vol_skill){
        int hab_id = h.getId();
        int vol_id = v.getId();
        Volunteer_Skill newVol_Skill = vsRepo.createVol_Skill(vol_skill, hab_id, vol_id);
        return newVol_Skill;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateVol_skill(@PathVariable(value = "id") int id, Volunteer_Skill vh) {
        vsRepo.updateVol_Skill(id, vh);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteVol_skill(@PathVariable(value = "id") int id, Volunteer_Skill vh){
        vsRepo.deleteVol_Skill(id, vh);
    }
}
