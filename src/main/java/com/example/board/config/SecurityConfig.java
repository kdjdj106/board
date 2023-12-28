package com.example.board.config;

import com.example.board.repository.UserRepository;
import com.example.board.service.login.CustomOAuth2UserService;
import com.example.board.service.login.CustomOidcUserService;
import com.example.board.service.login.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOidcUserService customOidcUserService;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/js/**", "/static/images/**", "/static/css/**","/static/scss/**");
    }

    @Bean
    SecurityFilterChain oauth2SecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests
//                .antMatchers("/loginProc").permitAll()
                .requestMatchers("/api/user")
                .access("hasAnyRole('SCOPE_profile','SCOPE_profile_image', 'SCOPE_email')")
//                .access("hasAuthority('SCOPE_profile')")
                .requestMatchers("/api/oidc")
                .access("hasRole('SCOPE_openid')")
                //.access("hasAuthority('SCOPE_openid')")
                .requestMatchers("/", "register")
                .permitAll()
                .anyRequest().authenticated());
        http.formLogin(formLogin -> formLogin
                .loginPage("/login").loginProcessingUrl("/loginProc").defaultSuccessUrl("/").permitAll());
        http.oauth2Login(oauth2 -> oauth2.userInfoEndpoint(
                userInfoEndpointConfig -> userInfoEndpointConfig
                        .userService(customOAuth2UserService)  // OAuth2
                        .oidcUserService(customOidcUserService)));  // OpenID Connect
        http.userDetailsService(customUserDetailsService);  // Form
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")));
        http.logout(logout -> logout
                .logoutSuccessUrl("/"));
        return http.build();
    }

}
