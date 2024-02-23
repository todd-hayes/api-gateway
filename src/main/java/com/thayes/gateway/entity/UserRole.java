package com.thayes.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@IdClass(UserRoleId.class)
@Table(name="roles")
@Setter @Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserRole extends BaseEntity<String> {

    @Id
    @Column(name="user_name")
    private String userName;

    @Id
    @Column(name="role_name")
    private String roleName;

    @ManyToOne
    @JoinColumn(name="user_name", insertable = false, updatable = false)
    @JsonIgnore
    private ApiUser user;

    public UserRole(String userName, String roleName) {
        this.userName = userName;
        this.roleName = roleName;
    }
}
