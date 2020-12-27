package com.TBDGis.TBDProyecto.services;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "vol_skill")
public class Vol_skillService {
    private final Vol_skillRepository vsRepo;
    Vol_skillService(Vol_skillRepository vsRepo){
        this.vsRepo = vsRepo;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Vol_skill> getAllVolSkill(){
        return this.vsRepo.getAllVolSkill();
    }

    @PostMapping("createVolHab")
    @ResponseBody
    public Vol_skill createVol_skill(@RequestBody Voluntario v, Habilidad h, Vol_skill vol_skil){
        int hab_id = h.getId();
        int vol_id = v.getId();
        Vol_skill newVol_skill = vsRepo.createVol_skil(vol_skil, hab_id, vol_id);
        return newVol_skill;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateVol_skill(@PathVariable(value = "id") int id, Vol_skill vh) {
        vsRepo.updateVol_skill(id, vh);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteVol_skill(@PathVariable(value = "id") int id, Vol_skill vh){
        vsRepo.deleteVol_skill(id, vh);
    }
}
