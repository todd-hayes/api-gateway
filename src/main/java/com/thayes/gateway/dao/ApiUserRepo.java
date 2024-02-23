package com.thayes.gateway.dao;

import com.thayes.gateway.entity.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiUserRepo extends JpaRepository<ApiUser, String> {

//    @Query("SELECT a FROM ApiUser a WHERE a.userName=?1")
//    Optional<ApiUser> findByUserName(String userName);
}
