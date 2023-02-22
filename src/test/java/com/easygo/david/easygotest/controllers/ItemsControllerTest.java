package com.easygo.david.easygotest.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@WebAppConfiguration
class ItemsControllerTest {

    private final static String BASE_URL = "/api/v1/survivors/items";
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getAllItems() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    void getItemRandomIdBadRequest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL+"/"+ UUID.randomUUID()))
                .andReturn();
        assertEquals(400,result.getResponse().getStatus());
    }
}