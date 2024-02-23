package com.thayes.gateway.controller;

import com.thayes.gateway.entity.UserRole;
import com.thayes.gateway.entity.UserRoleId;
import com.thayes.gateway.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RoleController {

    private RoleService roleService;

    RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public List<UserRole> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/roles/{id}")
    public UserRole getRole(@PathVariable UserRoleId id) {
        return roleService.getRole(id);
    }

    @PostMapping("/roles")
    public UserRole createUserRole(@RequestBody UserRole role) {
        return roleService.saveRole(role);
    }

    @PutMapping("/roles")
    public UserRole updateUserRole(@RequestBody UserRole role) {
        return roleService.saveRole(role);
    }

    @DeleteMapping("/roles")
    public void deleteUserRole(@RequestBody UserRole role) {
        roleService.deleteRole(role);
    }
}
