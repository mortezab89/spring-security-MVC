package com.mkyong.daoImpl;

import com.mkyong.dao.UserDetailsDao;
import com.mkyong.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {

    @Autowired
    private SessionFactory sessionFactory;

    public User findUserByUsername(String username) {
        return sessionFactory.getCurrentSession().get(User.class, username);
    }
}
