package com.example.demo.service;

import com.example.demo.domain.Pagination;
import com.example.demo.domain.ResponseWire;
import com.example.demo.domain.StarWarsPerson;
import com.example.demo.domain.StarWarsSpecies;
import com.example.demo.utils.ControllerMessage;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public interface StarWarsService {

    UUID putCharacterToDB(String name, String character) throws IOException;

    ResponseWire generateSameAndDifferentCharacter(UUID uuid, String character) throws IOException;

    String findRandomCharacterInMap(String character, Integer randomId);

    StarWarsPerson getRandomPeople() throws IOException;

    StarWarsSpecies getRandomSpecies() throws IOException;

    Pagination getAll(Pageable pageable, String character);

    ArrayList<StarWarsPerson> getAllPeopleFromDB(Pageable pageable);

    ArrayList<StarWarsSpecies> getAllSpeciesFromDB(Pageable pageable);

    ControllerMessage getResultByUUID(UUID uuid);

    Pagination generatePageablePeople(ArrayList<StarWarsPerson> arrayList, Pageable pageable);

    Pagination generatePageableSpecies(ArrayList<StarWarsSpecies> arrayList, Pageable pageable);

}
