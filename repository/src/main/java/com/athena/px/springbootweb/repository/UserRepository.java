package com.athena.px.springbootweb.repository;

import com.athena.px.springbootweb.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;


/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/4/23 15:50
 */
@Repository
public class UserRepository {

   /* private final DataSource dataSource;

    @Autowired
    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    public boolean saveUser(){
        return true;
    }

    public boolean createUser(User user){
        return true;
    }

}
