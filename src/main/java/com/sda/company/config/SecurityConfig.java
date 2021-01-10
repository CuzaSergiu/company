package com.sda.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@EnableWebSecurity - annotarea care ne specifica ca aceasta clasa ne configureaza securitatea
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Authentication Manager
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        // authenticationManagerBuilder - a realizat o autentificare in memoria
        // pentru a functiona obligatoriu passwordEncoder + setat role la fiecare user
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN", "USER")
                .and()
                .withUser("user").password(passwordEncoder().encode("1234")).roles("USER");
    }

    // Authorization
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        //pentru orice request permiteAll si foloseste httpbasic(autentificarea de baza, adica user si parola)
        // acest setup ne permite orice, fara securitate
//        httpSecurity
//                .authorizeRequests()
//                .anyRequest()
//                .permitAll()
//                .and()
//                .httpBasic();

        // acest setup ne va permite sa securizam accesul,
        // ADMIN full-acces la modificari , USER - are acces doar pe Employee sa faca modificari
        httpSecurity
                .authorizeRequests()

//                .antMatchers("/api/v1/company/*").hasRole("ADMIN")
//                .antMatchers("/api/v1/employee/*").hasAnyRole("ADMIN", "USER")
//                .anyRequest().authenticated()

                .anyRequest()
                .permitAll()
                .and()
                .httpBasic();

        // csrf - Cross Site Request Forgery.
        // cors - Cross-Origin Resource Sharing
        // csrf - cors - impiedica comunicarea dintre un browser si microserviciul de Spring,
        // cand le setam pe disable,  ne permite accesul in browser
        httpSecurity
                .csrf().disable().authorizeRequests()
                .and()
                .cors().disable().authorizeRequests();

    }

    // metoda care ne cripteaza parolele, obligatoriu sa ii atribuim annotarea @Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
