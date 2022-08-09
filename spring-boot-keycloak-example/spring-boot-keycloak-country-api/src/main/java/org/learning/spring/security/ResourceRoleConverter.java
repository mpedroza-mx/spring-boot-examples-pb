package org.learning.spring.security;


import com.nimbusds.jose.util.JSONObjectUtils;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class ResourceRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    private static final Logger LOG = LoggerFactory.getLogger(ResourceRoleConverter.class);
    private static final String REALM_ACCESS = "realm_access";
    private static final String ROLES = "roles";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        try {
            List<String> roles = JSONObjectUtils.getStringList((Map<String, Object>) jwt.getClaims().get(REALM_ACCESS),ROLES);
            return roles.stream()
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        } catch (ParseException e) {
            LOG.error(e.getMessage(),e);
            throw new RuntimeException("Error while trying to get user roles");
        }

    }
}

