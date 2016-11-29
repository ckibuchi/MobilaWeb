package com.mobila.ejb;



import com.mobila.model.User;

import java.util.List;

/**
 * Created by ckibuchi on 8/16/2016.
 */
public interface UserEJBI {
    public List<User> getAllUsers();
    public User SaveOrUpdate(String userdata) throws Exception;
    public User Authenticate(String username, String password) throws Exception;
    public User FindByuserName(String email);
}
