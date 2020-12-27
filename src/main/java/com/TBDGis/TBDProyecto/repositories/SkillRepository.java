package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.Skill;

import java.util.List;

public interface SkillRepository {
    public int countSkilles();
    public List<Skill> getAllSkilles();
    public Skill getSkillById(Integer id);
    public Skill createSkill(Skill habilidad);
    public void updateSkill(int id, Skill habilidad);
    public void deleteSkill(int id, Skill habilidad);
}
