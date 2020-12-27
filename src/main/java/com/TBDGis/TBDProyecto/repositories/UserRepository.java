package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.User;
import java.util.List;

public interface UserRepository {

    public List<User> getAllUser();
    public User getUserById(Integer id);
    public User createUser(User user);
    public void deleteUser(int id, User user);
    public User getUserByToken(String token);
    public String logIn(User user);
    public String logOut(User user);

}
