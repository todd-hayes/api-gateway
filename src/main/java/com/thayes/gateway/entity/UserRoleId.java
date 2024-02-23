package com.thayes.gateway.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter @NoArgsConstructor @EqualsAndHashCode
public class UserRoleId implements Serializable {

    private String userName;
    private String roleName;

    UserRoleId(String userName, String roleName) {
        this.userName = userName;
        this.roleName = roleName;
    }
}
