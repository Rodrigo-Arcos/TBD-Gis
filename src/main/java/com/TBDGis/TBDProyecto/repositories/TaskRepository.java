package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.Task;
import java.util.List;

public interface TaskRepository {

    public List<Task> getAllTasks();
    public Task getTasksByIds(Integer id_tarea, Integer id_emer, Integer id_est);
    public Task createTask(Task tarea);
    public int biggestId();
    public Task getTaskById(Integer id);
    public void updateTask(int id, Task tarea);
    public void deleteTask(int id, Task tarea);
    public List<Task> getInactivesTasks();
    public void deleteInactivesTasks(int hours);

}
