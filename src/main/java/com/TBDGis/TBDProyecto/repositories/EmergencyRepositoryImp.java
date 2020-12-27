package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.Emergency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class EmergencyRepositoryImp implements EmergencyRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countEmergency() {
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM emergencia").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Emergency> getAllEmergencies() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from emergencia")
                    .executeAndFetch(Emergency.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Emergency getEmergencyById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM emergencia WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(Emergency.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    /*
        Metodo que obtiene el mayor ID de la tabla emergencia
     */
    public int biggestIdEme(){
        try(Connection conn = sql2o.open()){
            Emergency temp = conn.createQuery("SELECT * FROM emergencia ORDER BY id DESC").executeAndFetchFirst(Emergency.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Emergency createEmergency(Emergency emergencia){
        int id = this.biggestIdEme() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO emergencia (id,nombre,id_institucion,finicio,ffin,descrip,invisible,longitude,latitude,location) values (:id,:emergenciaNombre,:id_institucion,:finicio,:ffin,:descrip,:invisible,:longitude,:latitude,:location)", true)
                    .addParameter("id", id)
                    .addParameter("emergenciaNombre", emergencia.getNombre())
                    .addParameter("id_institucion", emergencia.getId_institucion())
                    .addParameter("finicio", emergencia.getFinicio())
                    .addParameter("ffin", emergencia.getFfin())
                    .addParameter("descrip", emergencia.getDescrip())
                    .addParameter("invisible", emergencia.getInvisible())
                    .addParameter("longitude", emergencia.getLongitude())
                    .addParameter("latitude", emergencia.getLatitude())
                    .addParameter("location", emergencia.getLocation())
                    .executeUpdate().getKey();
            emergencia.setId(id);
            return emergencia;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }

    @Override
    public void updateEmergency(int id, Emergency emergencia) {
        String updateSql = "update emergencia set nombre=:emergenciaNombre,id_institucion=:id_institucion,finicio=:finicio,ffin=:ffin,descrip=:descrip where id = :idParam";

        try (Connection con = sql2o.open()) {
            Emergency valorAntiguo = con.createQuery("SELECT * FROM emergencia where id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(Emergency.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(emergencia.getNombre() != null){
                consulta.addParameter("emergenciaNombre", emergencia.getNombre());
            }else{
                consulta.addParameter("emergenciaNombre", valorAntiguo.getNombre());
            }
            if(emergencia.getId_institucion() != null){
                consulta.addParameter("id_institucion", emergencia.getId_institucion());
            }else{
                consulta.addParameter("id_institucion", valorAntiguo.getId_institucion());
            }
            if(emergencia.getFinicio() != null){
                consulta.addParameter("finicio", emergencia.getFinicio());
            }else{
                consulta.addParameter("finicio", valorAntiguo.getFinicio());
            }
            if(emergencia.getFfin() != null){
                consulta.addParameter("ffin", emergencia.getFfin());
            }else{
                consulta.addParameter("ffin", valorAntiguo.getFfin());
            }
            if(emergencia.getDescrip() != null){
                consulta.addParameter("descrip", emergencia.getDescrip());
            }else{
                consulta.addParameter("descrip", valorAntiguo.getDescrip());
            }
            consulta.executeUpdate();
            System.out.println("La emergencia se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void deleteEmergency(int id, Emergency emergencia){
        String deleteSql = "update emergencia set invisible=:invisible  where id = :idParam";
        try (Connection con = sql2o.open()) {
            Emergency valorAntiguo = con.createQuery("SELECT * FROM emergencia where id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(Emergency.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(emergencia.getInvisible() != null){
                consulta.addParameter("invisible", emergencia.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("La emergencia se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
