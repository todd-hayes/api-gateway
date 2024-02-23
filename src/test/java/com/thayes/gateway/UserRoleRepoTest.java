package com.thayes.gateway;

import com.thayes.gateway.dao.UserRoleRepo;
import com.thayes.gateway.entity.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRoleRepoTest {

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Test
    public void getAllUserRolesTest() {
        List<UserRole> roles = userRoleRepo.findAll();
        assertNotNull(roles);
        assertEquals(6, roles.size());
    }

    @Test
    public void getRolesByUsernameTest() {
        List<UserRole> rolesForUser = userRoleRepo.getRolesByUsername("admin");
        assertEquals(2, rolesForUser.size());
    }
}
