package com.TBDGis.TBDProyecto.repositories;
import com.TBDGis.TBDProyecto.models.Emergency_Skill;

import java.util.List;

public interface Eme_SkillRepository {
    public int countEme_habs();
    public List<Emergency_Skill> getAllEme_habs();
    public Emergency_Skill getEme_SkillById(Integer id);
    public Emergency_Skill createEme_Skill(Emergency_Skill eme_habilidad);
    public void updateEme_skill(int id, Emergency_Skill eme_habilidad);
    public void deleteEme_Skill(int id, Emergency_Skill eme_habilidad);
}
