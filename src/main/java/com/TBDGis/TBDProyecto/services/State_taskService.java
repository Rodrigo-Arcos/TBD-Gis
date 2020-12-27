package com.TBDGis.TBDProyecto.services;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "estado_tareas")

public class State_taskService {
    private final State_taskRepository state_taskRepository;
    State_taskService(State_taskRepository state_taskRepository){
        this.state_taskRepository = state_taskRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public List<State_task> getAllState_tasks() {
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
    public State_task getState_taskById(@PathVariable(value = "id") Integer id){
        return this.state_taskRepository.getState_taskById(id);
    }

    @PostMapping("/createState_task")
    @ResponseBody
    public State_task createState_task(@RequestBody State_task state_task){
        State_task result = state_taskRepository.createState_task(state_task);
        return result;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateState_task(@PathVariable(value = "id") int id, State_task state_task) {
        state_taskRepository.updateState_task(id, state_task);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteState_task(@PathVariable(value = "id") int id, State_task state_task){
        state_taskRepository.deleteState_task(id, state_task);
    }
}
