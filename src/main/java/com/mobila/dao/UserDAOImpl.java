package com.mobila.dao;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobila.dao.generic.GenericDaoImpl;
import com.mobila.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.password.StrongPasswordEncryptor;

import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

/**
 * Created by ckibuchi on 8/19/2016.
 */
@Stateless
public class UserDAOImpl extends GenericDaoImpl<User,Long> implements UserDao {
    ObjectMapper mapper = new ObjectMapper();
    StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
    public List<User> getAllUsers(){
        return findAll();
    }
    public User SaveOrUpdate(String userdata) throws Exception
    {
        //log.info(userdata);
        System.out.println("userdata "+userdata);
        User user= mapper.readValue(userdata, User.class);
       // if(user.getId()==null)
        //{
            user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
       // }
        if(user.getDatecreated()==null)
        {
            user.setDatecreated(new Date());
        }
        user=save(user);
        return user;
    }
    public User Authenticate(String email,String password) throws Exception
    {     Session session = (Session)getEm().unwrap((Class)Session.class);
        Criteria crit=session.createCriteria(User.class);
        crit.add((Criterion) Restrictions.eq("email",email));
        //crit.add((Criterion) Restrictions.eq("password",passwordEncryptor.encryptPassword(password)));
        List<User> users = crit.list();
      System.out.println("Users size "+users.size());
        if(users.size()>0) {
            User user= users.get(0);
           if (passwordEncryptor.checkPassword(password, user.getPassword())) {

                return user;
            }
            else
            {
                   // log.error("Wrong password");
                return null;
            }
        }
        else
            return null;
    }
    public User FindByuserName(String email)
    {     Session session = (Session)getEm().unwrap((Class)Session.class);
        Criteria crit=session.createCriteria(User.class);
        crit.add(Restrictions.disjunction()
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("phone", email)));
        /*crit.add((Criterion) Restrictions.eq("email",email));*/
        //crit.add((Criterion) Restrictions.eq("password",passwordEncryptor.encryptPassword(password)));
        List<User> users = crit.list();
      //  log.info("Users size "+users.size());
        if(users.size()>0) {
            User user= users.get(0);
      return user;
        }
        else
            return null;
    }

}