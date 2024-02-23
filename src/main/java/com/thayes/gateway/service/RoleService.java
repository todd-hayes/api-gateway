package com.thayes.gateway.service;

import com.thayes.gateway.entity.UserRole;
import com.thayes.gateway.entity.UserRoleId;

import java.util.List;
import java.util.Set;

public interface RoleService {
    UserRole saveRole(UserRole role);

    UserRole getRole(UserRoleId role);

    List<UserRole> getRolesForUser(String userName);

    List<UserRole> getAllRoles();

    UserRole updateRole(UserRole role);

    void deleteRole(UserRole role);

    void deleteAllRoles();
}
