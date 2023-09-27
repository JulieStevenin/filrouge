package com.appfilrouge.projetfilrouge.config;

import com.appfilrouge.projetfilrouge.services.AuthJwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                httpSecurityCorsConfigurer.disable();
            }
        });

        http.csrf(new Customizer<CsrfConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
                httpSecurityCsrfConfigurer.disable();

            }
        });


        http.authorizeHttpRequests(requests -> {
            requests

                    .requestMatchers("/user/register").permitAll()
                    .requestMatchers("/auth").permitAll()
                    .requestMatchers("/ad/all").permitAll()
                    .requestMatchers("/ad/createdAd").permitAll()
                    .requestMatchers("/ad/{adId}").permitAll()
                    .requestMatchers("tickets/byad/{adId}").permitAll()
                    .requestMatchers("/tickets/{id}").permitAll()
                    .requestMatchers(("/ad")).permitAll()
                    .requestMatchers(("/order/{id}")).permitAll()
                    .requestMatchers(("/order/byticket/{ticketId}")).permitAll()
                    .requestMatchers(("/order/validatesimple/{id}")).permitAll()
                    .requestMatchers(("ad/all/false/{bool}")).permitAll()
                    .requestMatchers("/ad/{adId}").permitAll()
                    .requestMatchers("/tickets/{id}").permitAll()
                    .anyRequest().authenticated();
        });

        //
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> {
            httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });


        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    AuthJwtTokenFilter authenticationJwtTokenFilter() {
        return new AuthJwtTokenFilter();
    }

}