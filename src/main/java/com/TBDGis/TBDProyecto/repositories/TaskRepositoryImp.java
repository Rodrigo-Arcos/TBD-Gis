package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.sql.Time;
import java.util.List;

@Repository
public class TaskRepositoryImp implements TaskRepository{

    @Autowired
    private Sql2o sql2o;


    @Override
    public List<Task> getAllTasks(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM tarea").executeAndFetch(Task.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public Task getTasksByIds(Integer id_tarea, Integer id_emer, Integer id_est){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM tarea WHERE id_emergencia = :id_emer AND id_estado = :id_est")
                    .addParameter("id_emer", id_emer)
                    .addParameter("id_est", id_est)
                    .executeAndFetchFirst(Task.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public int biggestId() {
        try (Connection conn = sql2o.open()) {
            Task temp = conn.createQuery("SELECT * FROM tarea ORDER BY id DESC").executeAndFetchFirst(Task.class);
            return temp.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }


    @Override
    public Task createTask(Task tarea){
        int id = this.biggestId() + 1;
        long now = System.currentTimeMillis();
        Time sqlTime = new Time(now);
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO tarea (id, id_emergencia, id_estado, nombre, finicio, ffin, descrip, cant_vol_inscritos, cant_vol_requeridos, invisible, hora) values(:id, :id_emergencia, :id_estado, :nombre, :finicio, :ffin, :descrip, :cant_vol_inscritos, :cant_vol_requeridos, :invisible, :hora)")
                    .addParameter("id", id)
                    .addParameter("id_emergencia", tarea.getId_emergencia())
                    .addParameter("id_estado", tarea.getId_estado())
                    .addParameter("nombre", tarea.getNombre())
                    .addParameter("finicio", tarea.getFinicio())
                    .addParameter("ffin", tarea.getFfin())
                    .addParameter("descrip", tarea.getDescrip())
                    .addParameter("cant_vol_inscritos", tarea.getCant_vol_inscritos())
                    .addParameter("cant_vol_requeridos", tarea.getCant_vol_requeridos())
                    .addParameter("invisible", tarea.getInvisible())
                    .addParameter("hora", sqlTime)
                    .executeUpdate().getKey();
            tarea.setId(id);
            return tarea;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public Task getTaskById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM tarea WHERE id = :tarea_id")
                    .addParameter("tarea_id", id)
                    .executeAndFetchFirst(Task.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public void updateTask(int id, Task tarea) {
        String updateSql = "update tarea set id_emergencia=:id_emer, nombre=:nombre, finicio=:finicio, ffin=:ffin, descrip=:descrip, cant_vol_inscritos=:cant_vol_inscritos, cant_vol_requeridos=:cant_vol_requeridos, hora=:hora where id = :idParam";

        try (Connection con = sql2o.open()) {
            Task valorAntiguo = con.createQuery("SELECT * FROM tarea where id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(Task.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(tarea.getId_emergencia() != null){
                consulta.addParameter("id_emer", tarea.getId_emergencia());
            }else{
                consulta.addParameter("id_emer", valorAntiguo.getId_emergencia());
            }
            if(tarea.getNombre() != null){
                consulta.addParameter("nombre", tarea.getNombre());
            }else{
                consulta.addParameter("nombre", valorAntiguo.getNombre());
            }
            if(tarea.getFinicio() != null){
                consulta.addParameter("finicio", tarea.getFinicio());
            }else{
                consulta.addParameter("finicio", valorAntiguo.getFinicio());
            }
            if(tarea.getFfin() != null){
                consulta.addParameter("ffin", tarea.getFfin());
            }else{
                consulta.addParameter("ffin", valorAntiguo.getFfin());
            }
            if(tarea.getDescrip() != null){
                consulta.addParameter("descrip", tarea.getDescrip());
            }else{
                consulta.addParameter("descrip", valorAntiguo.getDescrip());
            }
            if(tarea.getCant_vol_inscritos() != null){
                consulta.addParameter("cant_vol_inscritos", tarea.getCant_vol_inscritos());
            }else{
                consulta.addParameter("cant_vol_inscritos", valorAntiguo.getCant_vol_inscritos());
            }
            if(tarea.getCant_vol_requeridos() != null){
                consulta.addParameter("cant_vol_requeridos", tarea.getCant_vol_requeridos());
            }else{
                consulta.addParameter("cant_vol_requeridos", valorAntiguo.getCant_vol_requeridos());
            }
            if(tarea.getHora() != null){
                consulta.addParameter("hora", tarea.getHora());
            }else {
                consulta.addParameter("hora", valorAntiguo.getHora());
            }
            consulta.executeUpdate();
            System.out.println("La tarea se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void deleteTask(int id, Task tarea){
        String deleteSql = "update tarea set invisible=:invisible  where id = :idParam";
        try (Connection con = sql2o.open()) {
            Task valorAntiguo = con.createQuery("SELECT * FROM tarea where id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(Task.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(tarea.getInvisible() != null){
                consulta.addParameter("invisible", tarea.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("La tarea se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public List<Task> getInactivesTasks(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM tarea where cant_vol_inscritos=0").executeAndFetch(Task.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    @Override
    public void deleteInactivesTasks(int horas) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("SELECT deleteinactivestareas(:horas)")
                    .addParameter("horas", horas);
            System.out.println("Se eliminaron las tareas inactivas de hace " + horas + " horas.");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}