package com.xiaodong.blog.dao;

import com.xiaodong.blog.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by xiaodong on 15/11/29.
 */
public interface PassportDAO extends CrudRepository<User,String> {

    @Query(value = "SELECT * FROM user WHERE name=?",nativeQuery = true)
    public User getUserByName(String name);

    @Query(value = "SELECT * FROM user WHERE email=?",nativeQuery = true)
    public User getUserByEmail(String email);

    @Query(value = "SELECT * FROM user WHERE email=? and password=?",nativeQuery = true)
    public User signInValidate(String email, String password);
}
