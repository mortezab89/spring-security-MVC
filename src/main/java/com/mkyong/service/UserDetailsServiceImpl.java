package com.mkyong.service;

import com.mkyong.dao.UserDetailsDao;
import com.mkyong.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.User.UserBuilder;

import javax.transaction.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsDao userDetailsDao;

    @Transactional()
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userByUsername = userDetailsDao.findUserByUsername(username);
        UserBuilder userBuilder = null;
        if(userByUsername != null){
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.disabled(!userByUsername.isEnabled());
            userBuilder.password(userByUsername.getPassword());
            String[] authorities = userByUsername.getAuthorities()
                    .stream().map(a->a.getAuthority()).toArray((String[]::new));

            userBuilder.authorities(authorities);
        }else {
            throw new UsernameNotFoundException("User not found");
        }

        return userBuilder.build();
    }
}
