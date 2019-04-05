package com.example.demo.service;

import java.io.IOException;
import java.util.HashMap;

public interface ApiService {

    String createURL(String urlString, String method) throws IOException;

    HashMap<String, Integer> createPeopleMap() throws IOException;

    HashMap<String, Integer> createSpeciesMap() throws IOException;

}
