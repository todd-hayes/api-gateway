package com.thayes.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // query to get user information
        jdbcUserDetailsManager.setUsersByUsernameQuery("""
                SELECT user_name, password, active
                FROM api_users
                WHERE user_name = ?
                """);

        // query to get roles information
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("""
                SELECT user_name, role_name
                FROM roles
                WHERE user_name = ?
                """);

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/","/health").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/roles").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/roles/**").hasAuthority("USER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/roles").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/users").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/roles").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users").hasAuthority("SUPER_ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/roles").hasAuthority("SUPER_ADMIN")
        );
        // Use basic auth
        httpSecurity.httpBasic(Customizer.withDefaults());
        // Disable cross site
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

}
