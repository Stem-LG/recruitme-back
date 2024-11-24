package tn.louay.recruitme.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class KeycloakRoleConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @SuppressWarnings("unchecked")
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        // Extract realm_access roles
        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
        if (realmAccess != null && !realmAccess.isEmpty()) {
            authorities.addAll(extractRoles((List<String>) realmAccess.get("roles")));
        }

        // Extract resource_access.recruitme roles
        Map<String, Object> resourceAccess = (Map<String, Object>) jwt.getClaims().get("resource_access");
        if (resourceAccess != null && !resourceAccess.isEmpty()) {
            Map<String, Object> recruitmeAccess = (Map<String, Object>) resourceAccess.get("recruitme");
            if (recruitmeAccess != null && !recruitmeAccess.isEmpty()) {
                authorities.addAll(extractRoles((List<String>) recruitmeAccess.get("roles")));
            }
        }

        return new JwtAuthenticationToken(jwt, authorities, jwt.getClaim("preferred_username"));
    }

    private Collection<GrantedAuthority> extractRoles(List<String> roles) {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
