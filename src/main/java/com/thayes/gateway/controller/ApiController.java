package com.thayes.gateway.controller;

import com.thayes.gateway.entity.ApiUser;
import com.thayes.gateway.entity.Role;
import com.thayes.gateway.entity.UserRole;
import com.thayes.gateway.exception.UserNotFoundException;
import com.thayes.gateway.service.RoleService;
import com.thayes.gateway.service.RoleServiceImpl;
import com.thayes.gateway.service.UserService;
import com.thayes.gateway.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class ApiController {

    private final UserService userService;
    private RoleService roleService;

    @Autowired
    ApiController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public List<ApiUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public ApiUser getUser(@PathVariable String userName) throws UserNotFoundException {
        return userService.getUser(userName);
    }

    @PostMapping("/users")
    public ApiUser createUser(@RequestBody ApiUser user) {
        return userService.saveUser(user);
    }

    @PostMapping("/users/{userId}/roles/{role}")
    public ApiUser assignRoleToUser(@PathVariable String userName, @PathVariable Role role) {
        UserRole newUserRole = new UserRole();
        newUserRole.setUserName(userName);
        newUserRole.setRoleName(role.name());
        roleService.saveRole(newUserRole);
        return userService.getUser(userName);
    }

    @PutMapping("/users")
    public ApiUser updateUser(@RequestBody ApiUser user) {
        return userService.saveUser(user);
    }

    @PutMapping("/users/{userId}/roles/{role}")
    public ApiUser updateUserRole(@PathVariable String userName, @PathVariable Role role) {
        // Get current assigned role(s)
        List<UserRole> roles = roleService.getRolesForUser(userName);
        UserRole newUserRole = new UserRole(userName, role.name());
        if (!roles.contains(newUserRole))  // Only add role if it is not assigned to user
            roleService.saveRole(newUserRole);
        return userService.getUser(userName);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUserById(@PathVariable String userName) {
        userService.deleteUser(userName);
    }
}
