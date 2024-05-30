package be.kdg.team_5_phygital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import static org.springframework.security.web.util.matcher.RegexRequestMatcher.regexMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN","CLIENT")
                        .requestMatchers("/installation/**").hasAnyRole("ADMIN", "CLIENT","SUPERVISOR")
                        .requestMatchers("/web-app/**").permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/api/**"))
                        .permitAll()
                        .requestMatchers(antMatcher(HttpMethod.GET, "/js/**"), antMatcher(HttpMethod.GET, "/css/**"), antMatcher(HttpMethod.GET, "/images/**"), antMatcher(HttpMethod.GET, "/webjars/**"), regexMatcher(HttpMethod.GET, "\\.ico$")).permitAll().requestMatchers(antMatcher(HttpMethod.GET, "/")).permitAll()
                        .requestMatchers(
                                antMatcher(HttpMethod.POST, "/api/**"),
                                antMatcher(HttpMethod.PATCH, "/api/**"),
                                antMatcher(HttpMethod.DELETE, "/api/**")
                        ).authenticated()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied"));
//                .exceptionHandling(ex -> ex
//                        .authenticationEntryPoint((request, response, exception) -> {
//                            if (request.getRequestURI().startsWith("/api")) {
//                                // For API requests, respond with HTTP 401 (Unauthorized)
//                                response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                            } else {
//                                // For other requests, redirect to the custom access-denied page
//                                response.sendRedirect(request.getContextPath() + "/access-denied");
//                            }
//                        })
//                );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

