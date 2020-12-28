package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.Emergency;
import com.TBDGis.TBDProyecto.models.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class VolunteerRepositoryImp implements VolunteerRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Volunteer> getAllVolunteers(){
        try(Connection conn = sql2o.open()){
            final String query = "SELECT id, nombre, apellido, email, sexo, st_x(st_astext(location)) AS longitude, st_y(st_astext(location)) AS latitude FROM voluntario";
            return conn.createQuery(query).executeAndFetch(Volunteer.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Volunteer getVolunteerById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM voluntario WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(Volunteer.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    /*
        Metodo que obtiene el mayor ID de la tabla voluntario
     */
    public int biggestId(){
        try(Connection conn = sql2o.open()){
            Volunteer temp = conn.createQuery("SELECT * FROM voluntario ORDER BY id DESC").executeAndFetchFirst(Volunteer.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Volunteer createVolunteer(Volunteer voluntario){
        int id = this.biggestId() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO voluntario (id, nombre, apellido) VALUES(:id, :nombre, :fnacimiento)", true)
                    .addParameter("id", id)
                    .addParameter("nombre", voluntario.getNombre())
                    .addParameter("apellido", voluntario.getApellido())
                    .executeUpdate().getKey();
            voluntario.setId(id);
            return voluntario;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }
    /*
    @Override
    public void updateVolunteer(int id, Volunteer voluntario) {
        String updateSql = "UPDATE voluntario SET nombre=:nombre, apellido=:apellido WHERE id = :idParam";

        try (Connection con = sql2o.open()) {
            Volunteer valorAntiguo = con.createQuery("SELECT * FROM voluntario WHERE id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(Volunteer.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(voluntario.getNombre() != null){
                consulta.addParameter("nombre", voluntario.getNombre());
            }else{
                consulta.addParameter("nombre", valorAntiguo.getNombre());
            }
            if(voluntario.getApellido() != null){
                consulta.addParameter("apellido", voluntario.getApellido());
            }else{
                consulta.addParameter("apellido", valorAntiguo.getApellido());
            }
            consulta.executeUpdate();
            System.out.println("El voluntario se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }*/
    /*@Override
    public Emergency getEmergencyById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM emergencia WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(Emergency.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }*/

    /*
    dist = distance(emergecia, voluntarios)
    dist = dist*111
        si dist <= radio
            sacar id y nombre
        end
    end
    */
}