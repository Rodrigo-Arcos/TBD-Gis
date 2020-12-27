package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.Task_Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

public class Task_SkillRepositoryImp implements Task_SkillRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countTask_skills(){
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM tarea_habilidad").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Task_Skill> getAllTarea_habs() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from tarea_habilidad")
                    .executeAndFetch(Task_Skill.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Task_Skill getTask_skillById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM tarea_habilidad WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(Task_Skill.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int biggestId() {
        try (Connection conn = sql2o.open()) {
            Task_Skill temp = conn.createQuery("SELECT * FROM tarea_habilidad ORDER BY id DESC").executeAndFetchFirst(Task_Skill.class);
            return temp.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Task_Skill createTask_skill(Task_Skill tarea_habilidad) {
        int id = this.biggestId() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO tarea_habilidad (id, id_emehab,id_tarea) values (:id, :id_emehab,:id_tarea)", true)
                    .addParameter("id", id)
                    .addParameter("id_emehab", tarea_habilidad.getId_emehab())
                    .addParameter("id_tarea", tarea_habilidad.getId_tarea())
                    .executeUpdate().getKey();
            tarea_habilidad.setId(id);
            return tarea_habilidad;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateTask_skill(int id, Task_Skill tarea_habilidad) {
        String updateSql = "update tarea_habilidad set id_emehab=:id_emehab, id_tarea=:id_tarea where id = :idParam";

        try (Connection con = sql2o.open()) {
            Task_Skill valorAntiguo = con.createQuery("SELECT * FROM tarea_habilidad where id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(Task_Skill.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(tarea_habilidad.getId_emehab() != null){
                consulta.addParameter("id_emehab", tarea_habilidad.getId_emehab());
            }else{
                consulta.addParameter("id_emehab", valorAntiguo.getId_emehab());
            }
            if(tarea_habilidad.getId_tarea() != null){
                consulta.addParameter("id_tarea", tarea_habilidad.getId_tarea());
            }else{
                consulta.addParameter("id_tarea", valorAntiguo.getId_tarea());
            }
            consulta.executeUpdate();
            System.out.println("La Tarea_habilidad se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteTask_skill(int id, Task_Skill tarea_habilidad){
        String deleteSql = "update tarea_habilidad set invisible=:invisible  where id = :idParam";
        try (Connection con = sql2o.open()) {
            Task_Skill valorAntiguo = con.createQuery("SELECT * FROM tarea_habilidad where id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(Task_Skill.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(tarea_habilidad.getInvisible() != null){
                consulta.addParameter("invisible", tarea_habilidad.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("La tarea_habilidad se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
