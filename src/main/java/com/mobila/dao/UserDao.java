package com.mobila.dao;

import com.mobila.dao.generic.GenericDAO;
import com.mobila.model.User;

import java.util.List;

public interface UserDao extends GenericDAO<User,Long> {
    public List<User> getAllUsers();
    public User SaveOrUpdate(String userdata) throws Exception;
    public User Authenticate(String email,String password) throws Exception;
    public User FindByuserName(String email);

}