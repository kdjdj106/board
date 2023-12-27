package com.example.board.config;

import com.example.board.CustomAuthorityMapper;
import com.example.board.repository.UserRepository;
import com.example.board.service.login.CustomOAuth2UserService;
import com.example.board.service.login.CustomOidcUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    private CustomOidcUserService customOidcUserService;


    @Bean
    SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/api/user")
                .hasAnyRole("SCOPE_profile", "SCOPE_email")
//                .access("hasAuthority('SCOPE_profile')")
                .requestMatchers("/api/oidc")
                .hasRole("SCOPE_openid")
                //.access("hasAuthority('SCOPE_openid')")
                .requestMatchers("/")
                .permitAll()
                .anyRequest().authenticated());
        http.formLogin(formLogin -> formLogin.loginPage("/login").loginProcessingUrl("loginProc").defaultSuccessUrl("/").permitAll());
        http.oauth2Login(oauth2 -> oauth2.userInfoEndpoint(
                userInfoEndpointConfig -> userInfoEndpointConfig
                        .userService(customOAuth2UserService)
                        .oidcUserService(customOidcUserService)));
        http.logout(logout -> logout.logoutSuccessUrl("/"));
        http.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")));
        return http.build();
    }
    @Bean
    public GrantedAuthoritiesMapper customAuthorityMapper(){
        return new CustomAuthorityMapper();
    }
}
