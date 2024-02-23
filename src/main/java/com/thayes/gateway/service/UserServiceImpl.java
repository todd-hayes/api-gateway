package com.thayes.gateway.service;

import com.thayes.gateway.dao.ApiUserRepo;
import com.thayes.gateway.entity.ApiUser;
import com.thayes.gateway.entity.UserRole;
import com.thayes.gateway.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final ApiUserRepo apiUserRepo;
    private RoleService roleService;

    @Autowired
    public UserServiceImpl(ApiUserRepo apiUserRepo, RoleService roleService) {

        this.apiUserRepo = apiUserRepo;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public ApiUser saveUser(ApiUser apiUser) {
        return apiUserRepo.save(apiUser);
    }

    public ApiUser getUser(String userName) throws UserNotFoundException {
        Optional<ApiUser> result = apiUserRepo.findById(userName);
        if (result.isEmpty()) {
            throw new UserNotFoundException("User not found for username: " + userName);
        }
//        List<UserRole> roles = roleService.getRolesForUser(result.get().getUserName());
//        result.get().setUserRoles(roles);
        return result.get();
    }

    public List<ApiUser> getAllUsers() {

        return apiUserRepo.findAll();
    }

    @Override
    @Transactional
    public ApiUser updateUser(ApiUser apiUser) {
        return apiUserRepo.save(apiUser);
    }

    @Override
    @Transactional
    public void deleteUser(String userName) {
        apiUserRepo.deleteById(userName);
    }

    @Override
    @Transactional
    public void deleteAllUsers() {
        apiUserRepo.deleteAll();
    }
}
