package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.Volunteer_Skill;

import java.util.List;

public interface Volunteer_SkillRepository {

    public List<Volunteer_Skill> getAllVol_Skill();
    public Volunteer_Skill createVol_Skill(Volunteer_Skill vh, int hab_id, int v_id);
    public void updateVol_Skill(int id, Volunteer_Skill vh);
    public void deleteVol_Skill(int id, Volunteer_Skill vh);

}
