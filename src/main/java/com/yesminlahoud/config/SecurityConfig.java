package com.yesminlahoud.config;

import com.yesminlahoud.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UsuarioServiceImpl usuarioService;

    // método que faz a criptografia
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // esse método faz a autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    // esse método configura as páginas que cada usuário autenticado pode acessar
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/clientes/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/pedidos/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/produtos/**")
                        .hasRole("ADMIN")
                    .and()
                    .httpBasic();

        // authenticated() -> permite que qualquer usuário autenticado tenha acesso
        // permitAll() -> permite que todos os usuários tenham acesso
        // hasRole() -> permite que os usuários com um determinado papel acessem. Exemplo: hasRole("USER")
        // hasAuthority() -> apenas usuários que tem determinada authority podem acessar. Exemplo: hasAuthority("MANTER USUARIO")

        // and()-> volta para a raiz do objeto
        // formLogin() -> cria o formulário padrão de login do spring security ou indica o caminho para um formulário customizado
        // httpBasic() -> ao invés de usar um formulário, permite fazer uma requisição passando um header com as credenciais
    }
}
