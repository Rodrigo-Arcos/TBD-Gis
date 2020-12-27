package com.TBDGis.TBDProyecto.services;

import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@CrossOrigin
@RestController
public class TaskService {
    private final TaskRepository taskRepo;
    TaskService(TaskRepository taskRepo){
        this.taskRepo = taskRepo;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Task> getAllTask(){
        return this.taskRepo.getAllTasks();
    }

    @PostMapping("/create")
    @ResponseBody
    public Task createTask(@RequestBody Task task){ return taskRepo.createTask(task); }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Task getTaskById(@PathVariable(value = "id") Integer id){
        return this.taskRepo.getTaskById(id);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateTask(@PathVariable(value = "id") int id, Task task){
        taskRepo.updateTask(id, task);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteTask(@PathVariable(value = "id") int id, Task task){
        taskRepo.deleteTask(id, task);
    }

    @RequestMapping(value = "/deleteOldWithHour/{hours}")
    @ResponseBody
    public void deleteInactives(@PathVariable(value="hours") int hours) {
        taskRepo.deleteInactivesTasks(hours);
    }
}
