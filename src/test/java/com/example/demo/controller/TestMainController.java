package com.example.demo.controller;

import com.example.demo.component.CharacterMapperPeople;
import com.example.demo.component.CharacterMapperSpecies;
import com.example.demo.domain.Results;
import com.example.demo.domain.StarWarsPerson;
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

    @Test
    public void createCharacterName() throws Exception {
//        Mockito.when(characterMapperPeople.getMapForRandomGenerationPeople()
//        ).thenReturn(TestUtils.generateMapOf1Characters());
//        System.out.println("1");
//        Mockito.when(characterMapperSpecies.getMapForRandomGenerationSpecies()
//        ).thenReturn(TestUtils.generateMapOf1Species());
//        System.out.println("2");
//        StarWarsPerson starWarsPerson = TestUtils.generateLuke();
//        System.out.println(starWarsPerson.toString());
//        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String jsonPerson = objectWriter.writeValueAsString(starWarsPerson);
//        System.out.println(jsonPerson);
//        Mockito.when(apiService.createURL("https://swapi.co/api/people/1/", "GET")
//        ).thenReturn(jsonPerson);
//        System.out.println("obj mapper start");
//
//        Mockito.when(starWarsPersonRepository.save(starWarsPerson)).thenReturn(TestUtils.generateLuke());
//        System.out.println("Luke generated");
//        UUID uuidLuke = UUID.fromString("0a7ec959-fd82-4f12-beb9-bb2a59c77e39");
//        Mockito.when(starWarsPersonRepository.existsById(uuidLuke)).thenReturn(true);
//
//        Results results = TestUtils.generateResult();
//        System.out.println(results);
//        Mockito.when(starWarsResultsRepository.getOne(results.getUuid())).thenReturn(results);
//
//        Mockito.when(random.nextInt()).thenReturn(1);
//
//        Mockito.when(starWarsResultsRepository.save(results)).thenReturn(TestUtils.generateResult());
//
//        this.mockMvc.perform(post("/create/people/Luke Skywalker")).andDo(print()).
//                andExpect(status().isOk()).
//                andExpect(content().contentType("application/json;charset=UTF-8")).
//                andExpect(jsonPath("$.uuid").value("0a7ec959-fd82-4f12-beb9-bb2a59c77e00"));
    }


    @Test
    public void getCharacterSameAndDifferentRace() throws Exception {
        UUID uuid = UUID.fromString("0a7ec959-fd82-4f12-beb9-bb2a59c77e38");

        Mockito.when(starWarsResultsRepository.getOne(uuid)).thenReturn(TestUtils.generateResult());

        this.mockMvc.perform(get("/get/0a7ec959-fd82-4f12-beb9-bb2a59c77e38")).andDo(print()).andExpect(status().isOk()).
                andExpect(content().contentType("application/json;charset=UTF-8")).
                andExpect(jsonPath("$.response").value("The Luke Skywalker is human , as Luke Skywalker and not mammal as, Human"));

    }

}