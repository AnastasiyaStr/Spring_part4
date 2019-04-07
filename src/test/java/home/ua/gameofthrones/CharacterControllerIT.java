package home.ua.gameofthrones;

import home.ua.gameofthrones.controller.CharacterController;
import home.ua.gameofthrones.exceptions.NotFoundException;
import home.ua.gameofthrones.service.CharacterService;
import home.ua.gameofthrones.service.Impl.CharacterServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacterControllerIT {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Autowired
    private CharacterController controller;

    @Mock
    CharacterService characterService;
    @InjectMocks
    CharacterServiceImpl characterServiceImpl;
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }
    @Test
    public void contexLoads() throws Exception {
        assertNotNull(controller);
    }

    @Test
    public void givenGameUriWhenMockMvcThenGetStatus() throws Exception {
        mockMvc.perform(post("/characters/{characterName}", "Jon Snow"))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenGreetUriThenMockMvcThenGetContentType() throws Exception {
        mockMvc.perform(post("/characters/{characterName}", "Jon Snow"));
        mockMvc.perform(get("/characters"))
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void givenGreetURIWhenMockMvcThenGetJson() throws Exception {
        mockMvc.perform(post("/characters/{characterName}", "Jon Snow"));
        mockMvc.perform(get("/characters"))
                .andExpect(jsonPath("$.content[0].name").value("Jon Snow"));
    }
    @Test
    public void givenGreetURIWhenMockMvcThenGetJson1() throws Exception {
        mockMvc.perform(post("/characters/{characterId}", 271)).andExpect(status().isNotFound());;
       /* mockMvc.perform(get("/characters"))
                .andExpect(jsonPath("$.content[0].name").value("Jon Snow"));*/
    }

    @Test
    public void testFindTheGreatestFromAllData() throws Exception {
        when(characterService.collectDataAndSaveIntoDatabase("Name")).thenThrow(new NotFoundException("No such element"));
            mockMvc.perform(post("/characters/{characterName}", "Name")).andExpect(status().isNotFound());

    }

    @Test
    public void givenGreetURIWhenMockMvcThenGetJson8() throws Exception {
        mockMvc.perform(get("/characters/{characterId}", 271)).andExpect(status().isNotFound());
       /* mockMvc.perform(get("/characters"))
                .andExpect(jsonPath("$.content[0].name").value("Jon Snow"));*/
    }
   /* @Test
    public void testFindTheGreatestFromAllData1() throws Exception {
        //when(characterService.collectDataAndSaveIntoDatabase("Name")).thenThrow(new NotFoundException("No such element"));
        mockMvc.perform(post("/characters/{characterName}"," ")).andExpect(status().isNotFound());

    }*/


}

