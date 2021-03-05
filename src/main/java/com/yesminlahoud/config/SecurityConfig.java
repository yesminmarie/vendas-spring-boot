package com.yesminlahoud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//EnableWebSecurity já possui a anotation Configure, por isso não é necessário colocá-la aqui
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // método que faz a criptografia
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // esse método faz a autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder())
                .withUser("fulano")
                .password(passwordEncoder().encode("123"))
                .roles("USER");
    }

    // esse método configura as páginas que cada usuário autenticado pode acessar
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}