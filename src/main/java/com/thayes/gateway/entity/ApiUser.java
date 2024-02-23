package com.thayes.gateway.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="api_users")
@Setter @Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ApiUser extends BaseEntity<String> {

    @Id
    @Column(name="user_name")
    private String userName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    @Getter(AccessLevel.NONE)
    private String password;

    @Column(name="active")
    private boolean active;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    private List<UserRole> userRoles = new ArrayList<>();

    public ApiUser(String userName, String email, String password, boolean active) {
        this.userName = userName;
        this.email = email;
        setPassword(password);
        this.active = active;
    }

    private void setPassword(String password) {
        // Encrypt password before saving
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String result = encoder.encode(password);
        this.password = "{bcrypt}" + result;
    }
}
