package com.thayes.gateway;

import com.thayes.gateway.entity.ApiUser;

import java.util.UUID;

public class BaseUserTest {

    protected static ApiUser createRandomTestUser() {
        final String uuid = UUID.randomUUID().toString().replace("-","");
        String userName = "testuser" + uuid;
        String email = userName + "@testuser.com";
        String password = "password1";
        boolean active = true;
        return new ApiUser(userName, email, password, active);
    }
}
