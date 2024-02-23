package com.thayes.gateway.dao;

import com.thayes.gateway.entity.ApiUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApiUserDAO {

    void save(ApiUser apiUser);

    List<ApiUser> findAll();

    List<ApiUser> findByUserName(String userName);

    void updateApiUser(ApiUser apiUser);

    int deleteAll();
}
