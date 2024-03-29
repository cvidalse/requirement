package com.example.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMethod;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //se conecta al servicio que obtiene los detalles del usuario
    @Autowired
    private MyUserDetailsService userService;

    /**Configura el nivel de seguridad y quien puede acceder a cada direccion del sistema
     *
     * @param http Coleccion de documentos
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/rec*").permitAll()
                .antMatchers("/recovery**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers(HttpMethod.POST, "/forgot-password").permitAll()
                .antMatchers("/**", "/home","/check").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    /**Genera un objeto Autentification contiene la respuesta a la autentificacion
     *
     * @param authenticationManagerBuilder Coleccion de documentos
     *
     */
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userService);
    }
}