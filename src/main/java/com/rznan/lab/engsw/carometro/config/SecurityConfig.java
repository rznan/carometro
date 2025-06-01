package com.rznan.lab.engsw.carometro.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Locale;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/",
                                "/login",
                                "/salvarUsuario",
                                "/cursos/ver",
                                "/alunos/ver",
                                "/alunos/*/detalhes",
                                "/alunos/filtrar/**",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/uploads/**"
                                ).permitAll()  // Essas rotas são públicas
                        .anyRequest().hasRole("ADMIN")  // Todas as demais exigem ADMIN
                )
                .formLogin(form -> form
                        .loginPage("/adm")  // Página de login personalizada
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/adm", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
