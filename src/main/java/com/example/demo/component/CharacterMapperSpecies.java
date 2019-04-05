package com.example.demo.component;

import com.example.demo.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.io.IOException;
import java.util.HashMap;

@EnableScheduling
public class CharacterMapperSpecies {

    @Autowired
    ApiService apiService;

    private HashMap<String, Integer> mapForRandomGenerationSpecies;

    public HashMap<String, Integer> getMapForRandomGenerationSpecies() {
        return mapForRandomGenerationSpecies;
    }

    @Scheduled(fixedDelay = 1000000)
    public void generateSpeciesMap(){
        try {
            mapForRandomGenerationSpecies = apiService.createSpeciesMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

