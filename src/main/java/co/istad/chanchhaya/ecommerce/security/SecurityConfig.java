package co.istad.chanchhaya.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) {
        // TODO
        // 1. REST Architecture - STATELESS
        http.sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // 2. Endpoint policy (public/protected endpoint)
        http.authorizeHttpRequests(
                request -> request
                        .requestMatchers(
                                "/swagger-ui.html/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/scalar/**"
                        ).permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/categories/**").hasAnyRole("STAFF", "ADMIN")

                        .anyRequest().authenticated()
        );

        // 3. Authentication Mechanism (HTTP Basic Authentication, JWT, OAuth2)
        http.httpBasic(Customizer.withDefaults());

        // 4. Disable CSRF
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

}
