package com.example.diplproj.utils.annotations;

import com.example.diplproj.exceptions.AuthException;
import com.example.diplproj.utils.enums.Roles;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class HasRolesAspect {
    @Before("@annotation(hasRoles)")
    public void checkRoles(HasRoles hasRoles) {
        // Get the authentication object
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Extract roles from JWT
        List<String> roles = jwt.getClaimAsStringList("user_roles");

        // Check if the user has one of the required roles
        for (Roles requiredRole : hasRoles.value()) {
            if (roles.contains(requiredRole.name())) {
                return;
            }
        }

        throw new AuthException();
    }
}
