package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.State_Task;

import java.util.List;

public interface State_taskRepository {
    public int countState_tasks();
    public List<State_Task> getAllState_tasks();
    public State_Task getState_taskById(Integer id);
    public State_Task createState_task(State_Task estado_Tarea);
    public void updateState_task(int id, State_Task estado_tarea);
    public void deleteState_task(int id, State_Task estado_tarea);
}
