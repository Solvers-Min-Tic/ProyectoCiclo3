package com.solvers.proyectoempresa.security;


import com.solvers.proyectoempresa.controllers.LogoutHandler;
import com.solvers.proyectoempresa.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements UserDetailsService {


    private final LogoutHandler logoutHandler;

    public WebSecurityConfig(LogoutHandler logoutHandler) {
        this.logoutHandler = logoutHandler;
    }

    @Autowired
    private EmpleadoService service;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/", "/home","/**/*.js", "/**/*.css","/registro","/postregistro","/error").permitAll()
                .anyRequest().authenticated().and()
                .oauth2Login().loginPage("/login").defaultSuccessUrl("/inicio",true)
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll().defaultSuccessUrl("/inicio",true)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .addLogoutHandler(logoutHandler);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Traer el usuario de mi base de datos por el correo electronico
        com.solvers.proyectoempresa.entities.Empleado userObject = this.service.selectByUserName(username);

        System.out.println(username);
        if (userObject != null) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            //authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User(
                    userObject.getCorreoElectronico(),
                    userObject.getPassword(),
                    authorities
            );
        }

        throw new UsernameNotFoundException(
                "User '" + username + "' not found.");
    }

    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }*/
}
