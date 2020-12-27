package com.TBDGis.TBDProyecto.repositories;


import com.TBDGis.TBDProyecto.models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

public class SkillRepositoryImp implements SkillRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countSkilles() {
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM habilidad").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Skill> getAllSkilles() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from habilidad")
                    .executeAndFetch(Skill.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Skill getSkillById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM habilidad WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(Skill.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /*
        Metodo que obtiene el mayor ID de la tabla habilidad
     */
    public int biggestIdHab(){
        try(Connection conn = sql2o.open()){
            Skill temp = conn.createQuery("SELECT * FROM habilidad ORDER BY id DESC").executeAndFetchFirst(Skill.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Skill createSkill(Skill habilidad){
        int id = this.biggestIdHab() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO habilidad (id,descrip) values (:id,:descrip)", true)
                    .addParameter("id", id)
                    .addParameter("descrip", habilidad.getDescrip())
                    .executeUpdate().getKey();
            habilidad.setId(id);
            return habilidad;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }

    @Override
    public void updateSkill(int id, Skill habilidad) {
        String updateSql = "update habilidad set descrip=:descrip where id = :idParam";

        try (Connection con = sql2o.open()) {
            Skill valorAntiguo = con.createQuery("SELECT * FROM habilidad where id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(Skill.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(habilidad.getDescrip() != null){
                consulta.addParameter("descrip", habilidad.getDescrip());
            }else{
                consulta.addParameter("descrip", valorAntiguo.getDescrip());
            }
            consulta.executeUpdate();
            System.out.println("La Habilidad se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void deleteSkill(int id, Skill habilidad){
        String deleteSql = "update habilidad set invisible=:invisible  where id = :idParam";
        try (Connection con = sql2o.open()) {
            Skill valorAntiguo = con.createQuery("SELECT * FROM habilidad where id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(Skill.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(habilidad.getInvisible() != null){
                consulta.addParameter("invisible", habilidad.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("La Habilidad se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
