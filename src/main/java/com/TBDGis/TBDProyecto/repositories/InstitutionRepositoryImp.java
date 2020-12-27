package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class InstitutionRepositoryImp implements InstitutionRepository{
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countInstitutions(){
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM institucion").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Institution> getAllInstituciones(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from institucion")
                    .executeAndFetch(Institution.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Institution getInstitutionById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM institucion WHERE id = :i_id")
                    .addParameter("i_id", id)
                    .executeAndFetchFirst(Institution.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int biggestId(){
        try(Connection conn = sql2o.open()){
            Institution temp = conn.createQuery("SELECT * FROM institucion ORDER BY id DESC").executeAndFetchFirst(Institution.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Institution createInstitution(Institution institucion){
        int id = this.biggestId() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO institucion (id, nombre, descrip, invisible) values (:id, :nombreInstitucion, :descrip, :invisible)", true)
                    .addParameter("id", id)
                    .addParameter("nombreInstitucion", institucion.getNombre())
                    .addParameter("descrip", institucion.getDescrip())
                    .addParameter("invisible", institucion.getInvisible())
                    .executeUpdate().getKey();
            institucion.setId(id);
            return institucion;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateInstitution(int id, Institution institucion) {
        String updateSql = "update institucion set descrip=:descrip, nombre=:nombreInstitucion where id = :idParam";

        try (Connection con = sql2o.open()) {
            Institution valorAntiguo = con.createQuery("SELECT * FROM institucion where id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(Institution.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(institucion.getNombre() != null){
                consulta.addParameter("nombreInstitucion", institucion.getNombre());
            }else{
                consulta.addParameter("nombreInstitucion", valorAntiguo.getNombre());
            }
            if(institucion.getDescrip() != null){
                consulta.addParameter("descrip", institucion.getDescrip());
            }else{
                consulta.addParameter("descrip", valorAntiguo.getDescrip());
            }
            consulta.executeUpdate();
            System.out.println("La institucion se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteInstitution(int id, Institution institucion){
        String deleteSql = "update institucion set invisible=:invisible  where id = :idParam";
        try (Connection con = sql2o.open()) {
            Institution valorAntiguo = con.createQuery("SELECT * FROM institucion where id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(Institution.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(institucion.getInvisible() != null){
                consulta.addParameter("invisible", institucion.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("La institucion se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
