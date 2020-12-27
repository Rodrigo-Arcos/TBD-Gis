package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.Ranking;
import org.springframework.beans.factory.annotation.Autowired;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

public class RankingRepositoryImp implements RankingRepository {
    @Autowired
    private Sql2o sql2o;

    @Override
    public int countRankings(){
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM ranking").executeScalar(Integer.class);
        }
        return total;
    }

    @Override
    public List<Ranking> getAllRankings(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from ranking")
                    .executeAndFetch(Ranking.class);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Ranking getRankingById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM ranking WHERE id = :r_id")
                    .addParameter("r_id", id)
                    .executeAndFetchFirst(Ranking.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int biggestId(){
        try(Connection conn = sql2o.open()){
            Ranking temp = conn.createQuery("SELECT * FROM ranking ORDER BY id DESC").executeAndFetchFirst(Ranking.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public Ranking createRanking(Ranking ranking){
        int id = this.biggestId() + 1;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO ranking (id, id_tarea, id_voluntario, puntaje, flg_invitado, flg_participa, invisible) values (:id, :id_tarea, :id_voluntario, :puntaje, :flg_invitado, :flg_participa, :invisible)", true)
                    .addParameter("id", id)
                    .addParameter("id_tarea", ranking.getId_tarea())
                    .addParameter("id_voluntario", ranking.getId_voluntario())
                    .addParameter("puntaje", ranking.getPuntaje())
                    .addParameter("flg_invitado", ranking.getFlg_invitado())
                    .addParameter("flg_participa", ranking.getFlg_participa())
                    .addParameter("invisible", ranking.getInvisible())
                    .executeUpdate().getKey();
            ranking.setId(id);
            return ranking;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void updateRanking(int id, Ranking ranking) {
        String updateSql = "update ranking set id_tarea=:id_tarea, id_voluntario=:id_voluntario, puntaje=:puntaje, flg_invitado=:flg_invitado, flg_participa=:flg_participa where id = :idParam";

        try (Connection con = sql2o.open()) {
            Ranking valorAntiguo = con.createQuery("SELECT * FROM ranking where id = :idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(Ranking.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(ranking.getId_tarea() != null){
                consulta.addParameter("id_tarea", ranking.getId_tarea());
            }else{
                consulta.addParameter("id_tarea", valorAntiguo.getId_tarea());
            }
            if(ranking.getId_voluntario() != null){
                consulta.addParameter("id_voluntario", ranking.getId_voluntario());
            }else{
                consulta.addParameter("id_voluntario", valorAntiguo.getId_voluntario());
            }
            if(ranking.getPuntaje() != null){
                consulta.addParameter("puntaje", ranking.getPuntaje());
            }else{
                consulta.addParameter("puntaje", valorAntiguo.getPuntaje());
            }
            if(ranking.getFlg_invitado() != null){
                consulta.addParameter("flg_invitado", ranking.getFlg_invitado());
            }else{
                consulta.addParameter("flg_invitado", valorAntiguo.getFlg_invitado());
            }
            if(ranking.getFlg_participa() != null){
                consulta.addParameter("flg_participa", ranking.getFlg_participa());
            }else{
                consulta.addParameter("flg_participa", valorAntiguo.getFlg_participa());
            }
            consulta.executeUpdate();
            System.out.println("El ranking se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void deleteRanking(int id, Ranking ranking){
        String deleteSql = "update ranking set invisible=:invisible  where id = :idParam";
        try (Connection con = sql2o.open()) {
            Ranking valorAntiguo = con.createQuery("SELECT * FROM ranking where id = :idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(Ranking.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(ranking.getInvisible() != null){
                consulta.addParameter("invisible", ranking.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("El ranking se elimino correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
