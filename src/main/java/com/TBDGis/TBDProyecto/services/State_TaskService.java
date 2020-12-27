package com.TBDGis.TBDProyecto.services;


import com.TBDGis.TBDProyecto.models.State_Task;
import com.TBDGis.TBDProyecto.repositories.State_TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "estado_tareas")

public class State_TaskService {
    private final State_TaskRepository state_taskRepository;
    State_TaskService(State_TaskRepository state_taskRepository){
        this.state_taskRepository = state_taskRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<State_Task> getAllState_tasks() {
        return state_taskRepository.getAllState_tasks();
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ResponseBody
    public String countState_tasks(){
        int total = state_taskRepository.countState_tasks();
        return String.format("Tienes %s State_tasks!!", total);
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public State_Task getState_taskById(@PathVariable(value = "id") Integer id){
        return this.state_taskRepository.getState_taskById(id);
    }

    @PostMapping("/createState_task")
    @ResponseBody
    public State_Task createState_task(@RequestBody State_Task state_task){
        State_Task result = state_taskRepository.createState_task(state_task);
        return result;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateState_task(@PathVariable(value = "id") int id, State_Task state_task) {
        state_taskRepository.updateState_task(id, state_task);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteState_task(@PathVariable(value = "id") int id, State_Task state_task){
        state_taskRepository.deleteState_task(id, state_task);
    }
}
