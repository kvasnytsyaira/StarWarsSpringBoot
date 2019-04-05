package com.example.demo.component;

import com.example.demo.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.io.IOException;
import java.util.HashMap;

@EnableScheduling
public class CharacterMapperPeople {

    @Autowired
    ApiService apiService;

    private HashMap<String, Integer> mapForRandomGenerationPeople;

    public HashMap<String, Integer> getMapForRandomGenerationPeople() {
        return mapForRandomGenerationPeople;
    }

    @Scheduled(fixedDelay = 1000000)
    public void generatePeopleMap(){
        try {
            mapForRandomGenerationPeople = apiService.createPeopleMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

