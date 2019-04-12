package com.example.demo.utils;

import com.example.demo.domain.Results;
import com.example.demo.domain.StarWarsPerson;
import com.example.demo.domain.StarWarsSpecies;
import com.example.demo.repository.StarWarsPersonRepository;
import com.example.demo.service.StarWarsServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


public class TestUtils {

    public static HashMap<String, Integer> generateMapOfPeople() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("C-3PO", 1);
        map.put("Biggs Darklighter", 2);
        map.put("Luke Skywalker", 3);
        return map;
    }
    public static HashMap<String, Integer> generateMapOfSpecies() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Iktotchi", 1);
        map.put("Togrutao", 2);
        map.put("Togruta", 3);
        return map;
    }

    public static HashMap<String, Integer> generateMapOf3Characters() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Luke Skywalker", 1);
        map.put("Biggs Darklighter", 2);
        map.put("C-3PO", 3);
        return map;
    }

    public static StarWarsPerson generateLuke() {
        UUID uuid = UUID.fromString("0a7ec959-fd82-4f12-beb9-bb2a59c77e38");
        return new StarWarsPerson(uuid, "1999", new ArrayList<>(), "male", "brown",
                "black", "183", "https://swapi.co/api/planets/1/", "84",
                "Luke Skywalker", "light", "2014-12-10T15:59:50.509000Z", "2014-12-20T21:17:50.323000Z",
                new ArrayList<>(), new ArrayList<>(), "https://swapi.co/api/people/9/", new ArrayList<>());

    }  public static StarWarsPerson generateC3PO() {
        UUID uuid = UUID.fromString("0a7ec959-fd82-4f12-beb9-bb2a59c77e38");
        return new StarWarsPerson( "1999", new ArrayList<>(), "male", "brown",
                "black", "183", "https://swapi.co/api/planets/1/", "84",
                "Luke Skywalker", "light", "2014-12-10T15:59:50.509000Z", "2014-12-20T21:17:50.323000Z",
                new ArrayList<>(), new ArrayList<>(), "https://swapi.co/api/people/9/", new ArrayList<>());
    }

    public static StarWarsPerson generateBiggs() {
        UUID uuid = UUID.fromString("0a7ec959-fd82-4f12-beb9-bb2a59c77e60");
        return new StarWarsPerson(uuid, "1999", new ArrayList<>(), "male", "brown",
                "black", "183", "https://swapi.co/api/planets/1/", "84",
                "Luke Skywalker", "light", "2014-12-10T15:59:50.509000Z", "2014-12-20T21:17:50.323000Z",
                new ArrayList<>(), new ArrayList<>(), "https://swapi.co/api/people/9/", new ArrayList<>());
    }

    public static StarWarsSpecies generateIktotchi() {
        UUID uuid = UUID.fromString("0a7ec959-fd82-4f12-beb9-bb2a59c77e30");
        return new StarWarsSpecies( "Human", "mammal", "sentient",
                "180", "caucasian, black, asian, hispanic", "blonde, brown, black, red",
                "brown, blue, green, hazel, grey, amber", "120", "https://swapi.co/api/planets/9/",
                "Galactic Basic", new ArrayList<>(), new ArrayList<>(), "2014-12-10T13:52:11.567000Z", "2015-04-17T06:59:55.850671Z", "url");
    }

    public static Results generateResult() {
        ArrayList<Object> listR = new ArrayList<>();
        UUID uuid = UUID.fromString("0a7ec959-fd82-4f12-beb9-bb2a59c77e00");
        listR.add(generateLuke());
        listR.add(generateBiggs());
        listR.add(generateIktotchi());
        return new Results(uuid, listR, "people");
    }

    public static void putToDB3Characters(StarWarsPersonRepository starWarsPersonRepository, StarWarsServiceImpl starWarsService) {
        starWarsPersonRepository.save(generateLuke());
        starWarsPersonRepository.save(generateLuke());
        starWarsPersonRepository.save(generateBiggs());
    }
}

