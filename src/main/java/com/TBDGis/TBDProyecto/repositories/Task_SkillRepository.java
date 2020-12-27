package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.Task_Skill;

import java.util.List;

public interface Task_SkillRepository {
    public int countTask_skills();
    public List<Task_Skill> getAllTarea_habs();
    public Task_Skill createTask_skill(Task_Skill tarea_habilidad);
    public void updateTask_skill(int id, Task_Skill tarea_habilidad);
    public void deleteTask_skill(int id, Task_Skill tarea_habilidad);
    public Task_Skill getTask_skillById(Integer id);
}
