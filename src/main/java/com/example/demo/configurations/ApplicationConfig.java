package com.example.demo.configurations;

import com.example.demo.component.CharacterMapperPeople;
import com.example.demo.component.CharacterMapperSpecies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@Configuration
public class ApplicationConfig {

    @Bean
    public CharacterMapperPeople charactersMapperPeople(){
        return new CharacterMapperPeople();
    }

    @Bean
    public CharacterMapperSpecies charactersMapperSpecies(){
        return new CharacterMapperSpecies();
    }
}
