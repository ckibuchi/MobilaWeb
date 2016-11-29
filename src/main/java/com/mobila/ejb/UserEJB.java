package com.mobila.ejb;

/**
 * Created by ckibuchi on 10/28/2016.
 */


import com.mobila.dao.UserDao;
import com.mobila.model.User;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ckibuchi on 8/16/2016.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserEJB implements UserEJBI  {
    @Inject
    private UserDao userDao;
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User SaveOrUpdate(String userdata) throws Exception {
        return userDao.SaveOrUpdate(userdata);
    }
    @Override
    public User Authenticate(String email,String password) throws Exception
    {
        return userDao.Authenticate( email, password);
    }

    @Override
    public User FindByuserName(String email) {
        return userDao.FindByuserName(email);
    }

}
