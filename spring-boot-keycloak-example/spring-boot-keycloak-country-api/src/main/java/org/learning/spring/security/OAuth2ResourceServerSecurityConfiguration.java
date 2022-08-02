package org.learning.spring.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class OAuth2ResourceServerSecurityConfiguration {


  @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
  private String jwkSetUri;

  @Value("${spring.security.oauth2.resourceserver.jwt.jws-algorithm}")
  private String jwsAlgorithm;

  private static final String COUNTRIES_RESOURCE_PATH = "countries";

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .authorizeHttpRequests((authorize) -> authorize
            .mvcMatchers(HttpMethod.GET, "/" + COUNTRIES_RESOURCE_PATH + "/**")
            .hasAnyRole(Role.MANAGER.getValue(), Role.USER.getValue())
            .mvcMatchers(HttpMethod.POST, "/" + COUNTRIES_RESOURCE_PATH + "/**")
            .hasAnyRole(Role.MANAGER.getValue())
            .mvcMatchers(HttpMethod.PUT, "/" + COUNTRIES_RESOURCE_PATH + "/**")
            .hasAnyRole(Role.MANAGER.getValue())
            .mvcMatchers(HttpMethod.DELETE, "/" + COUNTRIES_RESOURCE_PATH + "/**")
            .hasAnyRole(Role.MANAGER.getValue())
            .anyRequest().authenticated()
        ).sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).csrf().disable()
        .oauth2ResourceServer((oauth2) -> oauth2.jwt(
            jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));

    return http.build();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withJwkSetUri(jwkSetUri)
        .jwsAlgorithm(SignatureAlgorithm.from(jwsAlgorithm)).build();
  }

  private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
    JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
    jwtConverter.setJwtGrantedAuthoritiesConverter(new ResourceRoleConverter());
    return jwtConverter;
  }


}
