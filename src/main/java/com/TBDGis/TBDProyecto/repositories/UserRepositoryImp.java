package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.User;
import com.TBDGis.TBDProyecto.models.Volunteer_Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class UserRepositoryImp implements UserRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public List<User> getAllUser(){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM users").executeAndFetch(User.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User getUserById(Integer id){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM users WHERE id = :v_id")
                    .addParameter("v_id", id)
                    .executeAndFetchFirst(User.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public User getUserByToken(String token){
        try(Connection conn = sql2o.open()){
            return conn.createQuery("SELECT * FROM users WHERE loginToken = :v_token")
                    .addParameter("v_token", token)
                    .executeAndFetchFirst(User.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int biggestId(){
        try(Connection conn = sql2o.open()){
            User temp = conn.createQuery("SELECT * FROM users ORDER BY id DESC").executeAndFetchFirst(User.class);
            return temp.getId();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public User createUser(User user){
        int id = this.biggestId() + 1;
        int invisible = 0;
        try(Connection conn = sql2o.open()){
            conn.createQuery("INSERT INTO users (id, name, mail, phone, password, logintoken , idrol, idvol, invisible) VALUES(:id, :name, :mail, :phone, :password, :logintoken , :idrol, :idvol, :invisible)", true)
                    .addParameter("id", id)
                    .addParameter("name", user.getName())
                    .addParameter("mail", user.getMail())
                    .addParameter("phone", user.getPhone())
                    .addParameter("password", user.getPassword())
                    .addParameter("logintoken", "0")
                    .addParameter("idrol", user.getIdRol())
                    .addParameter("idvol", user.getIdVol())
                    .addParameter("invisible", invisible)
                    .executeUpdate().getKey();
            user.setId(id);
            return user;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }


    @Override
    public void deleteUser(int id, User user) {
        String deleteSql = "UPDATE users SET invisible=:invisible WHERE id =:idParam";
        try (Connection con = sql2o.open()) {
            User valorAntiguo = con.createQuery("SELECT * FROM users WHERE id =:idPa")
                    .addParameter("idPa", id)
                    .executeAndFetchFirst(User.class);
            Query consulta = con.createQuery(deleteSql);
            consulta.addParameter("idParam", id);
            if(user.getInvisible() != null){
                consulta.addParameter("invisible", user.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("The User was delete successfully.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateUser(int id, User vh){
        String updateSql = "UPDATE users SET name=:name, mail=:mail, phone=:phone, password=:password, idrol=:idrol, invisible=:invisible, idvol=:idvol WHERE id =:idParam";

        try (Connection con = sql2o.open()) {
            User valorAntiguo = con.createQuery("SELECT * FROM users WHERE id =:idP")
                    .addParameter("idP", id)
                    .executeAndFetchFirst(User.class);
            Query consulta = con.createQuery(updateSql);
            consulta.addParameter("idParam", id);
            if(vh.getName() != null){
                consulta.addParameter("name", vh.getName());
            }else{
                consulta.addParameter("name", valorAntiguo.getName());
            }
            if(vh.getMail() != null){
                consulta.addParameter("mail", vh.getMail());
            }else{
                consulta.addParameter("mail", valorAntiguo.getMail());
            }
            if(vh.getPhone() != null){
                consulta.addParameter("phone", vh.getPhone());
            }else{
                consulta.addParameter("phone", valorAntiguo.getPhone());
            }
            if(vh.getPassword() != null){
                consulta.addParameter("password", vh.getPassword());
            }else{
                consulta.addParameter("password", valorAntiguo.getPassword());
            }
            if(vh.getIdRol() != null){
                consulta.addParameter("idrol", vh.getIdRol());
            }else{
                consulta.addParameter("idrol", valorAntiguo.getIdRol());
            }
            if(vh.getIdVol() != null){
                consulta.addParameter("idvol", vh.getIdVol());
            }else{
                consulta.addParameter("idvol", valorAntiguo.getIdVol());
            }
            if(vh.getInvisible() != null){
                consulta.addParameter("invisible", vh.getInvisible());
            }else{
                consulta.addParameter("invisible", valorAntiguo.getInvisible());
            }
            consulta.executeUpdate();
            System.out.println("El Usuario se actualizo correctamente.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User logIn(User user) {
        int visible = 0;
        try(Connection conn = sql2o.open()){
            List<User> findUser1s = conn.createQuery("select * from users where mail=:mail and password=:password and invisible=:invisible")
                    .addParameter("mail", user.getMail())
                    .addParameter("password", user.getPassword())
                    .addParameter("invisible", visible)
                    .executeAndFetch(User.class);
            if(findUser1s.size() == 1){
                String token = "1";
                try(Connection con = sql2o.open()){
                    con.createQuery("UPDATE users SET name=:name, mail=:mail, phone=:phone, password=:password, logintoken=:logintoken , idrol=:idrol, idvol=:idvol WHERE id=:id ", true)
                            .addParameter("id", findUser1s.get(0).getId())
                            .addParameter("name", findUser1s.get(0).getName())
                            .addParameter("mail", findUser1s.get(0).getMail())
                            .addParameter("phone", findUser1s.get(0).getPhone())
                            .addParameter("password", findUser1s.get(0).getPassword())
                            .addParameter("idvol", user.getIdVol())
                            .addParameter("logintoken", token)
                            .addParameter("idrol", findUser1s.get(0).getIdRol())
                            .executeUpdate().getKey();
                    findUser1s.get(0).setPassword("");
                    User UserWithoutPass = findUser1s.get(0);
                    return UserWithoutPass;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    user.setMail("");
                    user.setPassword("");
                    user.setIdRol(-1);
                    return user;
                }
            }else{
                user.setMail("");
                user.setPassword("");
                user.setIdRol(-1);
                return user;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            user.setMail("");
            user.setPassword("");
            user.setIdRol(-1);
            return user;
        }
    }
    @Override
    public String logOut(User user){
        int visible = 0;
        try(Connection conn = sql2o.open()){
            List<User> findUser1s = conn.createQuery("select * from users where name=:name and password=:password and invisible=:invisible")
                    .addParameter("name", user.getName())
                    .addParameter("password", user.getPassword())
                    .addParameter("invisible", visible)
                    .executeAndFetch(User.class);
            if(findUser1s.size() == 1){
                int token = 0;
                try(Connection con = sql2o.open()){
                    con.createQuery("UPDATE users SET name=:name, mail=:mail, phone=:phone, password=:password, logintoken=:logintoken , idrol=:idrol, idvol=:idvol WHERE id=:id ", true)
                            .addParameter("id", findUser1s.get(0).getId())
                            .addParameter("name", findUser1s.get(0).getName())
                            .addParameter("mail", findUser1s.get(0).getMail())
                            .addParameter("phone", findUser1s.get(0).getPhone())
                            .addParameter("password", findUser1s.get(0).getPassword())
                            .addParameter("idvol", user.getIdVol())
                            .addParameter("logintoken", token)
                            .addParameter("idrol", findUser1s.get(0).getIdRol())
                            .executeUpdate().getKey();
                    return "LogOut Successfully";
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return  "LogOut Fail";
                }
            }else{
                return "LogOut Fail";
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return " Deslogeado Fail";
        }
    }

}
