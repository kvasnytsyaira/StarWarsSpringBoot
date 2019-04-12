package com.example.demo.controller;

import com.example.demo.domain.Pagination;
import com.example.demo.domain.ResponseWire;
import com.example.demo.repository.StarWarsPersonRepository;
import com.example.demo.repository.StarWarsResultsRepository;
import com.example.demo.repository.StarWarsSpeciesRepository;
import com.example.demo.service.StarWarsServiceImpl;
import com.example.demo.utils.ControllerMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MainControllers {

    private StarWarsPersonRepository starWarsPersonRepository;
    private StarWarsSpeciesRepository starWarsSpeciesRepository;
    private StarWarsResultsRepository starWarsResultsRepository;
    private StarWarsServiceImpl starWarsServiceImpl;


    @Autowired
    public MainControllers(StarWarsPersonRepository starWarsPersonRepository, StarWarsSpeciesRepository starWarsSpeciesRepository, StarWarsResultsRepository starWarsResultsRepository, StarWarsServiceImpl starWarsService) {
        this.starWarsPersonRepository = starWarsPersonRepository;
        this.starWarsSpeciesRepository = starWarsSpeciesRepository;
        this.starWarsResultsRepository = starWarsResultsRepository;
        this.starWarsServiceImpl = starWarsService;
    }

    @PostMapping("/create/{character}/{name}")
    public ResponseWire createCharacter(@PathVariable String name, @PathVariable String character) {
        Object uuidCharacter = starWarsServiceImpl.putCharacterToDB(name, character);
        return starWarsServiceImpl.generateSameAndDifferentCharacter(uuidCharacter, character);
    }

    @GetMapping("/get/{uuid}")
    public ResponseEntity<ControllerMessage> getCharacterSameAndDifferentRace(@PathVariable UUID uuid) {
        ControllerMessage resultByUUID = starWarsServiceImpl.getResultByUUID(uuid);
        return ResponseEntity.ok().body(resultByUUID);
    }

    @GetMapping("/getAll/{character}")
    public ResponseEntity<Pagination> getAllData(@PageableDefault(value = 2) Pageable pageable, @PathVariable String character) {
        Pagination all = starWarsServiceImpl.getAll(pageable, character);
        return ResponseEntity.ok().body(all);
    }


}