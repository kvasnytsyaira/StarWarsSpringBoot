package com.example.demo.controller;

import com.example.demo.repository.StarWarsPersonRepository;
import com.example.demo.repository.StarWarsResultsRepository;
import com.example.demo.service.ApiServiceImpl;
import com.example.demo.service.StarWarsServiceImpl;
import com.example.demo.utils.TestUtils;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TestMainControllerDB {


    @Autowired
    MockMvc mockMvc;
    @MockBean
    ApiServiceImpl apiService;

    @Autowired
    StarWarsServiceImpl starWarsService;


    @Autowired
    StarWarsResultsRepository starWarsResultsRepository;

    @Autowired
    StarWarsPersonRepository starWarsPersonRepository;

    @Test
    public void getAllData() throws Exception {

        ArrayList<?> list = new ArrayList<>();
        TestUtils.putToDB3Characters(starWarsPersonRepository, starWarsService);

        this.mockMvc.perform(get("/getAll/people")).andDo(print()).andExpect(status().isOk()).
                andExpect(content().contentType("application/json;charset=UTF-8")).
                andExpect(jsonPath("$.list").isNotEmpty()).
                andExpect(jsonPath("$.list").isArray()).
                andExpect(jsonPath("$.url").value("http://localhost:8080/getAll/people?page=1"))
        ;
    }
}
