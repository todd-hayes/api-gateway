package com.thayes.gateway.service;

import com.thayes.gateway.entity.ApiUser;

import java.util.List;

public interface UserService {

    ApiUser saveUser(ApiUser apiUser);

    ApiUser getUser(String userName);

    List<ApiUser> getAllUsers();

    ApiUser updateUser(ApiUser apiUser);

    void deleteUser(String userName);

    void deleteAllUsers();
}
