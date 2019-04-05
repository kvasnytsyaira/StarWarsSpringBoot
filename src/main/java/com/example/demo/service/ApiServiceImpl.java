package com.example.demo.service;

import com.example.demo.domain.AnswerPeople;
import com.example.demo.domain.AnswerSpecies;
import com.example.demo.domain.StarWarsPerson;
import com.example.demo.domain.StarWarsSpecies;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

@Service
public class ApiServiceImpl implements ApiService {

    @Override
    public String createURL(String urlString, String method) throws IOException {
        URL url = new URL(urlString);
        URLConnection urlConnection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
        httpURLConnection.setRequestMethod(method);
        httpURLConnection.setRequestProperty("Content-Type", "application/json; utf-8");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.addRequestProperty("User-Agent", "Opera");
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(50000);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        String result = "try block did not work";

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
            result = String.valueOf(response);
        }
        httpURLConnection.disconnect();
        return result;
    }

    @Override
    public HashMap<String, Integer> createPeopleMap() throws IOException {
        HashMap<String, Integer> mapForRandomGenerationPeople = new HashMap<>();
        int k = 0;
        String people = createURL("https://swapi.co/api/people/", "GET");
        AnswerPeople answer;
        do {
            answer = new ObjectMapper().readValue(people, AnswerPeople.class);
            for (StarWarsPerson person : answer.getResults()) {
                mapForRandomGenerationPeople.put(person.getName(), Integer.valueOf(person.getUrl().replaceAll("[^0-9]", "")));
            }
            if (answer.getNext() != null) {
                people = createURL(answer.getNext(), "GET");
            }
            k++;
        } while (k < 9);
        return mapForRandomGenerationPeople;
    }

    @Override
    public HashMap<String, Integer> createSpeciesMap() throws IOException {
        HashMap<String, Integer> mapForRandomGenerationSpecies = new HashMap<>();
        int k = 0;
        String species = createURL("https://swapi.co/api/species/", "GET");
        AnswerSpecies answer;
        do {
            answer = new ObjectMapper().readValue(species, AnswerSpecies.class);
            for (StarWarsSpecies specie : answer.getResults()) {
                mapForRandomGenerationSpecies.put(specie.getName(), Integer.valueOf(specie.getUrl().replaceAll("[^0-9]", "")));
            }
            if (answer.getNext() != null) {
                species = createURL(answer.getNext(), "GET");
            }
            k++;
        } while (k < 4);
        return mapForRandomGenerationSpecies;
    }
}
