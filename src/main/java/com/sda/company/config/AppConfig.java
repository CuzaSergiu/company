package com.sda.company.config;

import com.sda.company.components.CustomFakerEmployee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan - annotarea care ne scaneaza proiectul pentru a gasi clasele component;
//trebuie sa ii oferim un path catre acea clasa
@ComponentScan("com.sda.company.components")
public class AppConfig {

    //custom @Bean - care ne returneaza un CustomFakerEmployee,
    //obligatoriu trebuie annotat @Bean pentru a se putea realiza injectarea mai departe printr-un constructor
    @Bean
    public CustomFakerEmployee customFakerEmployee() {
        return new CustomFakerEmployee();
    }

}
