package com.mkyong.dao;

import com.mkyong.entity.User;

public interface UserDetailsDao {

    User findUserByUsername(String username);
}
