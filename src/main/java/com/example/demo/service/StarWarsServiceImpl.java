package com.example.demo.service;

import com.example.demo.component.CharacterMapperPeople;
import com.example.demo.component.CharacterMapperSpecies;
import com.example.demo.customExceptions.CustomIOException;
import com.example.demo.customExceptions.CustomNoSuchCharacterException;
import com.example.demo.customExceptions.NoSuchResultUUIDException;
import com.example.demo.domain.*;
import com.example.demo.repository.StarWarsPersonRepository;
import com.example.demo.repository.StarWarsResultsRepository;
import com.example.demo.repository.StarWarsSpeciesRepository;
import com.example.demo.utils.ControllerMessage;
import com.example.demo.utils.ResponceForMethodGenerateSameAndDifferentPerson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@PropertySource("classpath:application.properties")


public class StarWarsServiceImpl implements StarWarsService {


    private StarWarsPersonRepository starWarsPersonRepository;
    private StarWarsSpeciesRepository starWarsSpeciesRepository;
    private StarWarsResultsRepository starWarsResultsRepository;
    private CharacterMapperPeople characterMapperPeople;
    private CharacterMapperSpecies characterMapperSpecies;
    private ApiService apiService;

    @Autowired
    public StarWarsServiceImpl(StarWarsPersonRepository starWarsPersonRepository, StarWarsSpeciesRepository starWarsSpeciesRepository, StarWarsResultsRepository starWarsResultsRepository, CharacterMapperPeople characterMapperPeople, CharacterMapperSpecies characterMapperSpecies, ApiService apiService) {
        this.starWarsPersonRepository = starWarsPersonRepository;
        this.starWarsSpeciesRepository = starWarsSpeciesRepository;
        this.starWarsResultsRepository = starWarsResultsRepository;
        this.characterMapperPeople = characterMapperPeople;
        this.characterMapperSpecies = characterMapperSpecies;
        this.apiService = apiService;
    }

    @Override
    public Object putCharacterToDB(String name, String character) {

        if (character.equals("people")) {
            return putPeopleToDB(name);
        } else if (character.equals("species")) {
            return putSpeciesToDB(name);
        } else {
            throw new CustomNoSuchCharacterException("There is no such character : " + character);
        }
    }

    @Override
    public StarWarsPerson putPeopleToDB(String name) {
        try {
            if (characterMapperPeople.getMapForRandomGenerationPeople().containsKey(name)) {
                Integer peopleId = characterMapperPeople.getMapForRandomGenerationPeople().get(name);
                String json = apiService.createURL("https://swapi.co/api/people/" + peopleId, "GET");
                StarWarsPerson objFromJson = new ObjectMapper().readValue(json, StarWarsPerson.class);
                return starWarsPersonRepository.save(objFromJson);
            } else {
                throw new CustomNoSuchCharacterException("There is no character with name " + name);
            }
        } catch (IOException e) {
            throw new CustomIOException("Something went wrong!");
        }
    }

    @Override
    public StarWarsSpecies putSpeciesToDB(String name) {
        try {
            if (characterMapperSpecies.getMapForRandomGenerationSpecies().containsKey(name)) {
                Integer speciesId = characterMapperSpecies.getMapForRandomGenerationSpecies().get(name);
                String json = apiService.createURL("https://swapi.co/api/species/" + speciesId, "GET");
                StarWarsSpecies objFromJson = new ObjectMapper().readValue(json, StarWarsSpecies.class);
                return starWarsSpeciesRepository.save(objFromJson);
            } else {
                throw new CustomNoSuchCharacterException("There is no character with name " + name);
            }
        } catch (IOException e) {
            throw new CustomIOException("Something went wrong!");
        }

    }

    @Override
    public ResponseWire generateSameAndDifferentCharacter(Object object, String character) {

        if (character.equals("people")) {
            return generatePeople((StarWarsPerson) object);
        } else if (character.equals("species")) {
            return generateSpecies((StarWarsSpecies) object);
        } else {
            throw new CustomNoSuchCharacterException("There is no such character !");
        }
    }

    @Override
    public ResponseWire generatePeople(StarWarsPerson starWarsPerson) {
        try {
            ArrayList<Object> listSameDifferentRace = new ArrayList<>();
            listSameDifferentRace.add(0, starWarsPerson);
            StarWarsPerson characterSameRace = getRandomPeople();
            listSameDifferentRace.add(1, characterSameRace);
            StarWarsSpecies characterDifferentRace = getRandomSpecies();
            listSameDifferentRace.add(2, characterDifferentRace);
            Results results = new Results(listSameDifferentRace, "people");
            Results savedResult = starWarsResultsRepository.save(results);
            ResponseWire responseWire = new ResponseWire();
            responseWire.setUuid(savedResult.getUuid().toString());
            return responseWire;
        } catch (IOException e) {
            throw new CustomIOException("Something went wrong!");
        }
    }

    @Override
    public ResponseWire generateSpecies(StarWarsSpecies starWarsSpecies) {
        ArrayList<Object> listSameDifferentRace = new ArrayList<>();
        try {
            listSameDifferentRace.add(0, starWarsSpecies);
            StarWarsSpecies characterSameRace = getRandomSpecies();
            listSameDifferentRace.add(1, characterSameRace);
            StarWarsPerson characterDifferentRace = getRandomPeople();
            listSameDifferentRace.add(2, characterDifferentRace);
            Results results = new Results(listSameDifferentRace, "species");
            Results savedResult = starWarsResultsRepository.save(results);
            ResponseWire responseWire = new ResponseWire();
            responseWire.setUuid(savedResult.getUuid().toString());
            return responseWire;
        } catch (IOException e) {
            throw new CustomIOException("Something went wrong!");
        }
    }


    @Override
    public String findRandomCharacterInMap(String character, Integer randomId) {
        if (character.equals("people")) {
            return characterMapperPeople.getMapForRandomGenerationPeople()
                    .entrySet()
                    .stream()
                    .filter(entry -> randomId.equals(entry.getValue()))
                    .map(Map.Entry::getKey).findFirst().get();
        } else if (character.equals("species")) {
            return characterMapperSpecies.getMapForRandomGenerationSpecies()
                    .entrySet()
                    .stream()
                    .filter(entry -> randomId.equals(entry.getValue()))
                    .map(Map.Entry::getKey).findFirst().get();
        }
        return null;
    }

    @Override
    public StarWarsPerson getRandomPeople() throws IOException {
        Random random = new Random();
        Integer randomIdPeople = random.nextInt(characterMapperPeople.getMapForRandomGenerationPeople().size() + 1);
        String randomCharacterName = findRandomCharacterInMap("people", randomIdPeople);
        if (randomCharacterName == null) {
            throw new CustomNoSuchCharacterException("Character has not have name");
        }
        if (starWarsPersonRepository.byName(randomCharacterName) == null) {
            String getRandomPeople = apiService.createURL("https://swapi.co/api/people/" + randomIdPeople, "GET");
            StarWarsPerson starWarsPerson = new ObjectMapper().readValue(getRandomPeople, StarWarsPerson.class);
            return starWarsPersonRepository.save(starWarsPerson);
        }
        return starWarsPersonRepository.byName(randomCharacterName);
    }

    @Override
    public StarWarsSpecies getRandomSpecies() throws IOException {
        Random random = new Random();
        Integer randomIdSpecies = random.nextInt(characterMapperSpecies.getMapForRandomGenerationSpecies().size() + 1);
        String randomCharacterName = findRandomCharacterInMap("species", randomIdSpecies);

        if (randomCharacterName == null) {
            throw new CustomNoSuchCharacterException("Character has not have name");
        }
        if (starWarsSpeciesRepository.byName(randomCharacterName) == null) {
            String getRandomSpecies = apiService.createURL("https://swapi.co/api/species/" + randomIdSpecies, "GET");
            StarWarsSpecies starWarsSpecies = new ObjectMapper().readValue(getRandomSpecies, StarWarsSpecies.class);
            return starWarsSpeciesRepository.save(starWarsSpecies);
        }
        return starWarsSpeciesRepository.byName(randomCharacterName);
    }

    @Override
    public Pagination getAll(Pageable pageable, String character) {
        if (character.equals("people")) {
            ArrayList<StarWarsPerson> allFromDB = getAllPeopleFromDB(pageable);
            return generatePageablePeople(allFromDB, pageable);
        } else if (character.equals("species")) {
            ArrayList<StarWarsSpecies> allFromDB = getAllSpeciesFromDB(pageable);
            return generatePageableSpecies(allFromDB, pageable);
        } else {
            throw new CustomNoSuchCharacterException("There is no such character !");
        }
    }

    @Override
    public ArrayList<StarWarsPerson> getAllPeopleFromDB(Pageable pageable) {
        List<StarWarsPerson> all = starWarsPersonRepository.allPageable(pageable);
        return new ArrayList<>(all);
    }

    @Override
    public ArrayList<StarWarsSpecies> getAllSpeciesFromDB(Pageable pageable) {
        List<StarWarsSpecies> all = starWarsSpeciesRepository.allPageable(pageable);
        return new ArrayList<>(all);
    }

    @Override
    public Pagination generatePageablePeople(ArrayList<StarWarsPerson> arrayList, Pageable pageable) {
        String url = "http://localhost:8080/getAll/people?page=" + pageable.next().getPageNumber();
        if (arrayList.size() < pageable.next().getPageSize()) {
            url = "no next page";
        }
        return new Pagination(url, arrayList);
    }

    @Override
    public Pagination generatePageableSpecies(ArrayList<StarWarsSpecies> arrayList, Pageable pageable) {
        String url = "http://localhost:8080/getAll/species?page=" + pageable.next().getPageNumber();
        if (arrayList.size() < pageable.next().getPageSize()) {
            url = "no next page";
        }
        return new Pagination(url, arrayList);
    }

    @Override
    public ControllerMessage getResultByUUID(UUID uuid) {
        ResponceForMethodGenerateSameAndDifferentPerson message;
        Results result;
        try {
            result = starWarsResultsRepository.getOne(uuid);
        } catch (NumberFormatException e) {
            throw new NoSuchResultUUIDException("UUID is invalid!");
        }
        ArrayList<Object> resultList = result.getList();

        if (result.getRace().equals("people")) {
            StarWarsPerson starWarsPerson = (StarWarsPerson) resultList.get(0);
            StarWarsPerson starWarsPerson2 = (StarWarsPerson) resultList.get(1);
            StarWarsSpecies starWarsSpecies2 = (StarWarsSpecies) resultList.get(2);
            ControllerMessage controllerMessage = new ControllerMessage();
            message = new ResponceForMethodGenerateSameAndDifferentPerson(starWarsPerson, starWarsPerson2, starWarsSpecies2);
            controllerMessage.setResponse(message.people_Species(message));
            return controllerMessage;
        } else if (result.getRace().equals("species")) {
            StarWarsSpecies starWarsSpecies = (StarWarsSpecies) resultList.get(0);
            StarWarsSpecies starWarsSpecies2 = (StarWarsSpecies) resultList.get(1);
            StarWarsPerson starWarsPerson2 = (StarWarsPerson) resultList.get(2);
            message = new ResponceForMethodGenerateSameAndDifferentPerson(starWarsSpecies, starWarsSpecies2, starWarsPerson2);
            ControllerMessage controllerMessage = new ControllerMessage();
            controllerMessage.setResponse(message.species_People(message));
            return controllerMessage;
        }
        return new ControllerMessage();
    }

}


