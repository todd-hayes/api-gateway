package com.thayes.gateway.dao;

import com.thayes.gateway.entity.UserRole;
import com.thayes.gateway.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface UserRoleRepo extends JpaRepository<UserRole, UserRoleId> {

    @Query("Select r From UserRole r Where userName = ?1")
    List<UserRole> getRolesByUsername(String userName);
}
