package com.example.demo.controller;

import com.example.demo.component.CharacterMapperPeople;
import com.example.demo.component.CharacterMapperSpecies;
import com.example.demo.domain.Results;
import com.example.demo.domain.StarWarsPerson;
import com.example.demo.domain.StarWarsSpecies;
import com.example.demo.repository.StarWarsPersonRepository;
import com.example.demo.repository.StarWarsResultsRepository;
import com.example.demo.repository.StarWarsSpeciesRepository;
import com.example.demo.service.ApiServiceImpl;
import com.example.demo.service.StarWarsServiceImpl;
import com.example.demo.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TestMainController {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    ApiServiceImpl apiService;

    @Autowired
    StarWarsServiceImpl starWarsService;

    @MockBean
    CharacterMapperPeople characterMapperPeople;
    @MockBean
    CharacterMapperSpecies characterMapperSpecies;

    @MockBean
    StarWarsResultsRepository starWarsResultsRepository;

    @MockBean
    StarWarsPersonRepository starWarsPersonRepository;

    @MockBean
    StarWarsSpeciesRepository starWarsSpeciesRepository;

    @MockBean
    Random random;

//    @Test
//    public void TestCreateCharacter() throws Exception {
//        Mockito.when(characterMapperPeople.getMapForRandomGenerationPeople()
//        ).thenReturn(TestUtils.generateMapOfPeople());
//        StarWarsPerson starWarsPerson = TestUtils.generateC3PO();
//        String json = new ObjectMapper().writeValueAsString(starWarsPerson);
//        int peopleId = 1;
//        Mockito.when(apiService.createURL("https://swapi.co/api/people/" + peopleId, "GET")
//        ).thenReturn(json);
//        Mockito.when(starWarsPersonRepository.save(starWarsPerson))
//                .thenReturn(starWarsPerson);
//        Mockito.when(random
//                .nextInt(characterMapperPeople.getMapForRandomGenerationPeople().size() + 1))
//                .thenReturn(1);
//        Mockito.when(starWarsPersonRepository.byName(starWarsPerson.getName()))
//                .thenReturn(TestUtils.generateBiggs());
//        Mockito.when(characterMapperSpecies.getMapForRandomGenerationSpecies()
//        ).thenReturn(TestUtils.generateMapOfSpecies());
//        StarWarsSpecies starWarsSpecies = TestUtils.generateIktotchi();
//        String jsonS = new ObjectMapper().writeValueAsString(starWarsSpecies);
//        Mockito.when(random
//                .nextInt(characterMapperSpecies.getMapForRandomGenerationSpecies().size() + 1))
//                .thenReturn(1);
//        Mockito.when(starWarsSpeciesRepository.byName(starWarsSpecies.getName()))
//                .thenReturn(starWarsSpecies);
//        int randomIdSpecies = 1;
//        Mockito.when(apiService.createURL("https://swapi.co/api/species/" + randomIdSpecies, "GET"))
//                .thenReturn(jsonS);
//
//        Results results = TestUtils.generateResult();
//        Mockito.when(starWarsResultsRepository.save(results)).thenReturn(results);
//        this.mockMvc.perform(post("http://localhost:8080/create/people/C-3PO"))
//                .andDo(print()).
//                andExpect(status().isOk()).
//                andExpect(content().contentType("application/json;charset=UTF-8")).
//                andExpect(jsonPath("$.uuid").value("0a7ec959-fd82-4f12-beb9-bb2a59c77e00"));
//    }


    @Test
    public void getCharacterSameAndDifferentRace() throws Exception {
        UUID uuid = UUID.fromString("0a7ec959-fd82-4f12-beb9-bb2a59c77e38");

        Mockito.when(starWarsResultsRepository.getOne(uuid)).thenReturn(TestUtils.generateResult());
        this.mockMvc.perform(get("/get/0a7ec959-fd82-4f12-beb9-bb2a59c77e38")).andDo(print()).andExpect(status().isOk()).
                andExpect(content().contentType("application/json;charset=UTF-8")).
                andExpect(jsonPath("$.response").value("The Luke Skywalker is human , as Luke Skywalker and not mammal as, Human"));

    }

}