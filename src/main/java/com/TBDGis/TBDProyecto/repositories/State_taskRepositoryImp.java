package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.State_Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import java.util.List;

public class State_taskRepositoryImp implements State_taskRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countState_tasks(){
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM estado_tarea").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<State_Task> getAllState_tasks() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from estado_tarea")
                    .executeAndFetch(State_Task.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public State_Task getState_taskById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM estado_tarea WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(State_Task.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /*
        Metodo que obtiene el mayor ID de la tabla Estado_tarea
     */
    public int biggestIdEstado_tarea(){
        try(Connection conn = sql2o.open()){
            State_Task temp = conn.createQuery("SELECT * FROM estado_tarea ORDER BY id DESC").executeAndFetchFirst(State_Task.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public State_Task createState_task(State_Task estado_tarea){
        int id = this.biggestIdEstado_tarea() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO estado_tarea (id,descrip) values (:id,:descrip)", true)
                    .addParameter("id", id)
                    .addParameter("descrip", estado_tarea.getDescrip())
                    .executeUpdate().getKey();
            estado_tarea.setId(id);
            return estado_tarea;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }

    @Override
    public void updateState_task(int id, State_Task estado_tarea) {
        String updateSql = "update estado_tarea set descrip=:descrip where id = :idParam";

        try (Connection con = sql2o.open()) {
            State_Task valorAntiguo = con.createQuery("SELECT * FROM estado_tarea where id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(State_Task.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(estado_tarea.getDescrip() != null){
                consulta.addParameter("descrip", estado_tarea.getDescrip());
            }else{
                consulta.addParameter("descrip", valorAntiguo.getDescrip());
            }
            consulta.executeUpdate();
            System.out.println("La Estado_tarea se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void deleteState_task(int id, State_Task estado_tarea){
        String deleteSql = "update eme_habilidad set invisible=:invisible  where id = :idParam";
        try (Connection con = sql2o.open()) {
            State_Task valorAntiguo = con.createQuery("SELECT * FROM eme_habilidad where id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(State_Task.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(estado_tarea.getInvisible() != null){
                consulta.addParameter("invisible", estado_tarea.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("La Estado_tarea se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
