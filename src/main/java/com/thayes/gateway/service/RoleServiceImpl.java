package com.thayes.gateway.service;

import com.thayes.gateway.dao.UserRoleRepo;
import com.thayes.gateway.entity.UserRole;
import com.thayes.gateway.entity.UserRoleId;
import com.thayes.gateway.exception.RoleNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private UserRoleRepo userRoleRepo;

    RoleServiceImpl(UserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    @Transactional
    public UserRole saveRole(UserRole role) {
        return userRoleRepo.save(role);
    }

    @Override
    public UserRole getRole(UserRoleId role) {
        Optional<UserRole> result = userRoleRepo.findById(role);
        if (result.isEmpty()) {
            throw new RoleNotFoundException("Role not found for role: " + role);
        }
        return result.get();
    }

    @Override
    public List<UserRole> getRolesForUser(String userName) {
        return userRoleRepo.getRolesByUsername(userName);
    }

    @Override
    public List<UserRole> getAllRoles() {
        return userRoleRepo.findAll();
    }

    @Override
    @Transactional
    public UserRole updateRole(UserRole role) {
        return userRoleRepo.save(role);
    }

    @Override
    @Transactional
    public void deleteRole(UserRole role) {
        userRoleRepo.delete(role);
    }

    @Override
    @Transactional
    public void deleteAllRoles() {
        userRoleRepo.deleteAll();
    }
}
