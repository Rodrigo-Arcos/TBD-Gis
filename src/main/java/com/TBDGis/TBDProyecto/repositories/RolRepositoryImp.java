package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class RolRepositoryImp implements RolRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<Rol> getAllRol(){
        try(Connection conn = sql2o.open()){
            System.out.println("try");
            return conn.createQuery("SELECT * FROM rol").executeAndFetch(Rol.class);
        }catch(Exception e){
            System.out.println("catch");
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Rol getRolById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM rol WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(Rol.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int biggestId(){
        try(Connection conn = sql2o.open()){
            Rol temp = conn.createQuery("SELECT * FROM rol ORDER BY id DESC").executeAndFetchFirst(Rol.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Rol createRol(Rol rol){
        int id = this.biggestId() + 1;
        int invisible = 0;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO rol (id, name, description, invisible) VALUES(:id, :name, :description, :invisible)", true)
                    .addParameter("id", id)
                    .addParameter("name", rol.getName())
                    .addParameter("description", rol.getDescription())
                    .addParameter("invisible", invisible)
                    .executeUpdate().getKey();
            rol.setId(id);
            return rol;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }

    @Override
    public void deleteRol(int id, Rol rol){
        String deleteSql = "update rol set invisible=:invisible  where id = :idParam";
        try (Connection con = sql2o.open()) {
            Rol valorAntiguo = con.createQuery("SELECT * FROM rol where id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(Rol.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(rol.getInvisible() != null){
                consulta.addParameter("invisible", rol.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("El rol se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


}
