package com.TBDGis.TBDProyecto.services;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "task_hab")
public class Task_skillService {
    private final Task_skillRepository task_skillRepository;
    Task_skillService(Task_skillRepository task_skillRepository){
        this.task_skillRepository = task_skillRepository;
    }

    @GetMapping("/getAll")
    public List<Task_skill> getAllTask_skill() {
        return task_skillRepository.getAllTarea_habs();
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Task_skill getTask_skillById(@PathVariable(value = "id") Integer id){
        return this.task_skillRepository.getTask_skillById(id);
    }

    @GetMapping("/count")
    public String countTask_skills(){
        int total = task_skillRepository.countTask_skills();
        return String.format("Tienes %s tareas_habilidades!!", total);
    }

    @PostMapping("/create")
    @ResponseBody
    public Task_skill createTask_skill(@RequestBody Task_skill task_skill){
        Task_skill result = task_skillRepository.createTask_skill(task_skill);
        return result;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateTask_skill(@PathVariable(value = "id") int id, Task_skill task_skill){
        task_skillRepository.updateTask_skill(id, task_skill);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteTask_skill(@PathVariable(value = "id") int id, Task_skill task_skill){
        task_skillRepository.deleteTask_skill(id, task_skill);
    }
}
