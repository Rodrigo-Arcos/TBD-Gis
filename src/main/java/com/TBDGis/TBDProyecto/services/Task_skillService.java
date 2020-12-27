package com.TBDGis.TBDProyecto.services;

import com.TBDGis.TBDProyecto.models.Task_Skill;
import com.TBDGis.TBDProyecto.repositories.Task_SkillRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "task_hab")
public class Task_skillService {
    private final Task_SkillRepository task_skillRepository;
    Task_skillService(Task_SkillRepository task_skillRepository){
        this.task_skillRepository = task_skillRepository;
    }

    @GetMapping("/getAll")
    public List<Task_Skill> getAllTask_skill() {
        return task_skillRepository.getAllTarea_habs();
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Task_Skill getTask_skillById(@PathVariable(value = "id") Integer id){
        return this.task_skillRepository.getTask_skillById(id);
    }

    @GetMapping("/count")
    public String countTask_skills(){
        int total = task_skillRepository.countTask_skills();
        return String.format("Tienes %s tareas_habilidades!!", total);
    }

    @PostMapping("/create")
    @ResponseBody
    public Task_Skill createTask_skill(@RequestBody Task_Skill task_skill){
        Task_Skill result = task_skillRepository.createTask_skill(task_skill);
        return result;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateTask_skill(@PathVariable(value = "id") int id, Task_Skill task_skill){
        task_skillRepository.updateTask_skill(id, task_skill);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteTask_skill(@PathVariable(value = "id") int id, Task_Skill task_skill){
        task_skillRepository.deleteTask_skill(id, task_skill);
    }
}
