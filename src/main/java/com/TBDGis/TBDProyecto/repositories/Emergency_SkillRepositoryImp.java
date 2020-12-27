package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.Emergency_Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class Emergency_SkillRepositoryImp implements Emergency_SkillRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countEme_habs(){
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM eme_habilidad").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Emergency_Skill> getAllEme_habs() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from eme_habilidad")
                    .executeAndFetch(Emergency_Skill.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Emergency_Skill getEme_SkillById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM eme_habilidad WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(Emergency_Skill.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    /*
        Metodo que obtiene el mayor ID de la tabla eme_habilidad
     */
    public int biggestIdeme_hab(){
        try(Connection conn = sql2o.open()){
            Emergency_Skill temp = conn.createQuery("SELECT * FROM eme_habilidad ORDER BY id DESC").executeAndFetchFirst(Emergency_Skill.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Emergency_Skill createEme_Skill(Emergency_Skill eme_habilidad){
        int id = this.biggestIdeme_hab() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO eme_habilidad (id,id_emergencia,id_habilidad) values (:id,:id_emer,:id_hab)", true)
                    .addParameter("id", id)
                    .addParameter("id_emer", eme_habilidad.getId_emergencia())
                    .addParameter("id_hab", eme_habilidad.getId_habilidad())
                    .executeUpdate().getKey();
            eme_habilidad.setId(id);
            return eme_habilidad;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }

    @Override
    public void updateEme_skill(int id, Emergency_Skill eme_habilidad) {
        String updateSql = "update eme_habilidad set id_emergencia=:id_emer, id_habilidad=:id_hab where id = :idParam";

        try (Connection con = sql2o.open()) {
            Emergency_Skill valorAntiguo = con.createQuery("SELECT * FROM eme_habilidad where id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(Emergency_Skill.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(eme_habilidad.getId_emergencia() != null){
                consulta.addParameter("id_emer", eme_habilidad.getId_emergencia());
            }else{
                consulta.addParameter("id_emer", valorAntiguo.getId_emergencia());
            }
            if(eme_habilidad.getId_habilidad() != null){
                consulta.addParameter("id_hab", eme_habilidad.getId_habilidad());
            }else{
                consulta.addParameter("id_hab", valorAntiguo.getId_habilidad());
            }
            consulta.executeUpdate();
            System.out.println("La Emergencia_habilidad se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void deleteEme_Skill(int id, Emergency_Skill eme_habilidad){
        String deleteSql = "update eme_habilidad set invisible=:invisible  where id = :idParam";
        try (Connection con = sql2o.open()) {
            Emergency_Skill valorAntiguo = con.createQuery("SELECT * FROM eme_habilidad where id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(Emergency_Skill.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(eme_habilidad.getInvisible() != null){
                consulta.addParameter("invisible", eme_habilidad.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("La Emergencia_habilidad se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
