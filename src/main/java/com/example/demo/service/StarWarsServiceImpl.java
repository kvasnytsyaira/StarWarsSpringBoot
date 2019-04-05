package com.example.demo.service;

import com.example.demo.component.CharacterMapperPeople;
import com.example.demo.component.CharacterMapperSpecies;
import com.example.demo.customExceptions.CustomIOException;
import com.example.demo.customExceptions.CustomNoSuchCharacterException;
import com.example.demo.customExceptions.EntityNotFoundException;
import com.example.demo.domain.*;
import com.example.demo.repository.StarWarsPersonRepository;
import com.example.demo.repository.StarWarsResultsRepository;
import com.example.demo.repository.StarWarsSpeciesRepository;
import com.example.demo.utils.ControllerMessage;
import com.example.demo.utils.ResponceForMethodGenerateSameAndDifferentPerson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public UUID putCharacterToDB(String name, String character) {

        if (characterMapperPeople.getMapForRandomGenerationPeople().containsKey(name) || characterMapperSpecies.getMapForRandomGenerationSpecies().containsKey(name)) {
            if (character.equals("people")) {
                try {
                    Integer peopleId = characterMapperPeople.getMapForRandomGenerationPeople().get(name);
                    String json = null;
                    json = apiService.createURL("https://swapi.co/api/" + character + "/" + peopleId, "GET");
                    StarWarsPerson objFromJson = null;
                    objFromJson = new ObjectMapper().readValue(json, StarWarsPerson.class);
                    StarWarsPerson person = starWarsPersonRepository.save(objFromJson);
                    return person.getId();
                } catch (IOException e) {
                    throw new CustomIOException("Something went wrong!");
                }
            } else if (character.equals("species")) {
                try {
                    Integer speciesId = characterMapperSpecies.getMapForRandomGenerationSpecies().get(name);
                    String json = null;
                    json = apiService.createURL("https://swapi.co/api/" + character + "/" + speciesId, "GET");
                    StarWarsSpecies objFromJson = null;
                    objFromJson = new ObjectMapper().readValue(json, StarWarsSpecies.class);
                    StarWarsSpecies species = starWarsSpeciesRepository.save(objFromJson);
                    return species.getId();
                } catch (IOException e) {
                    throw new CustomIOException("Something went wrong!");
                }
            } else {
                throw new CustomNoSuchCharacterException("There is no such character !");
            }
        } else {
            throw new CustomNoSuchCharacterException("There is no character with name " + name);
        }
    }

    @Override
    public ResponseWire generateSameAndDifferentCharacter(UUID characterUuid, String character) {
        if (starWarsPersonRepository.existsById(characterUuid) || starWarsSpeciesRepository.existsById(characterUuid)) {
            Results savedResult;
            ArrayList<Object> listSameDifferentRace = new ArrayList<>();
            Results results;
            if (character.equals("people")) {
                try {
                    StarWarsPerson starWarsPerson = starWarsPersonRepository.getOne(characterUuid);
                    listSameDifferentRace.add(0, starWarsPerson);
                    StarWarsPerson characterSameRace = getRandomPeople();
                    listSameDifferentRace.add(1, characterSameRace);
                    StarWarsSpecies characterDifferentRace = getRandomSpecies();
                    listSameDifferentRace.add(2, characterDifferentRace);
                    results = new Results(listSameDifferentRace, "people");
                    savedResult = starWarsResultsRepository.save(results);
                    ResponseWire responseWire = new ResponseWire();
                    responseWire.setUuid(savedResult.getUuid().toString());
                    return responseWire;
                } catch (IOException e) {
                    throw new CustomIOException("Something went wrong!");
                }
            } else if (character.equals("species")) {
                try {
                    StarWarsSpecies starWarsSpecies = starWarsSpeciesRepository.getOne(characterUuid);
                    listSameDifferentRace.add(0, starWarsSpecies);
                    StarWarsSpecies characterSameRace = getRandomSpecies();
                    listSameDifferentRace.add(1, characterSameRace);
                    StarWarsPerson characterDifferentRace = getRandomPeople();
                    listSameDifferentRace.add(2, characterDifferentRace);
                    results = new Results(listSameDifferentRace, "species");
                    savedResult = starWarsResultsRepository.save(results);
                    ResponseWire responseWire = new ResponseWire();
                    responseWire.setUuid(savedResult.getUuid().toString());
                    return responseWire;
                } catch (IOException e) {
                    throw new CustomIOException("Something went wrong!");
                }
            } else {
                throw new CustomNoSuchCharacterException("There is no such character !");
            }
        } else {
            throw new EntityNotFoundException("There is no character with id :" + characterUuid + " in database!");
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
        Integer randomIdPeople = random.nextInt(characterMapperPeople.getMapForRandomGenerationPeople().size()) + 1;
        String randomCharacterName = findRandomCharacterInMap("people", randomIdPeople);
        if (randomCharacterName == null) {
            throw new CustomNoSuchCharacterException("Character has not have name");
        }
        StarWarsPerson starWarsPerson;
        if (starWarsPersonRepository.byName(randomCharacterName) == null) {
            String getRandomPeople = apiService.createURL("https://swapi.co/api/people/" + randomIdPeople, "GET");
            starWarsPerson = new ObjectMapper().readValue(getRandomPeople, StarWarsPerson.class);
            return starWarsPersonRepository.save(starWarsPerson);
        }
        return starWarsPersonRepository.byName(randomCharacterName);
    }

    @Override
    public StarWarsSpecies getRandomSpecies() throws IOException {
        Random random = new Random();
        Integer randomIdSpecies = random.nextInt(characterMapperSpecies.getMapForRandomGenerationSpecies().size()) + 1;
        String randomCharacterName = findRandomCharacterInMap("species", randomIdSpecies);
        if (randomCharacterName == null) {
            throw new CustomNoSuchCharacterException("Character has not have name");
        }
        StarWarsSpecies starWarsSpecies;
        if (starWarsSpeciesRepository.byName(randomCharacterName) == null) {
            String getRandomSpecies = apiService.createURL("https://swapi.co/api/species/" + randomIdSpecies, "GET");
            starWarsSpecies = new ObjectMapper().readValue(getRandomSpecies, StarWarsSpecies.class);
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
        Results result = starWarsResultsRepository.getOne(uuid);
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


