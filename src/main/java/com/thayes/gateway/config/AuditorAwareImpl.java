package com.thayes.gateway.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        String userName;
        try {
            userName = ((User) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal()).getUsername();
        } catch(NullPointerException npe) {
            userName = "system";
        }
        return Optional.of(userName);
    }
}
