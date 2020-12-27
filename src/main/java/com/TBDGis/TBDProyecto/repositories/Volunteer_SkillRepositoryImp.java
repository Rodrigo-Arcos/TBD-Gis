package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.Volunteer_Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class Volunteer_SkillRepositoryImp implements Volunteer_SkillRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Volunteer_Skill> getAllVol_Skill(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM vol_habilidad").executeAndFetch(Volunteer_Skill.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public int biggestId(){
        try(Connection conn = sql2o.open()){
            Volunteer_Skill temp = conn.createQuery("SELECT * FROM vol_habilidad ORDER BY id DESC").executeAndFetchFirst(Volunteer_Skill.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Volunteer_Skill createVol_Skill(Volunteer_Skill vh, int hab_id, int v_id){
        int id = this.biggestId() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO vol_habilidad (id, id_habilidad, id_voluntario) values(:id, :hab_id, :v_id)")
                    .addParameter("id", id)
                    .addParameter("hab_id", hab_id)
                    .addParameter("v_id", v_id)
                    .executeUpdate().getKey();
            vh.setId(id);
            return vh;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateVol_Skill(int id, Volunteer_Skill vh) {
        String updateSql = "UPDATE vol_habilidad SET id_habilidad=:id_h, id_voluntario=:id_v WHERE id = :idParam";

        try (Connection con = sql2o.open()) {
            Volunteer_Skill valorAntiguo = con.createQuery("SELECT * FROM vol_habilidad WHERE id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(Volunteer_Skill.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(vh.getId_habilidad() != null){
                consulta.addParameter("id_h", vh.getId_habilidad());
            }else{
                consulta.addParameter("id_h", valorAntiguo.getId_habilidad());
            }
            if(vh.getId_voluntario() != null){
                consulta.addParameter("id_v", vh.getId_voluntario());
            }else{
                consulta.addParameter("id_v", valorAntiguo.getId_voluntario());
            }
            consulta.executeUpdate();
            System.out.println("La tabla intermedia Voluntario_Habilidad se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteVol_Skill(int id, Volunteer_Skill vh) {
        String deleteSql = "UPDATE vol_habilidad SET invisible=:invisible WHERE id = :idParam";
        try (Connection con = sql2o.open()) {
            Volunteer_Skill valorAntiguo = con.createQuery("SELECT * FROM vol_habilidad WHERE id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(Volunteer_Skill.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(vh.getInvisible() != null){
                consulta.addParameter("invisible", vh.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("El voluntario se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
