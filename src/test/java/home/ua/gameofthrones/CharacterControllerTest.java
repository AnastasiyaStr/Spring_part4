//package home.ua.gameofthrones;
//
//
//import home.ua.gameofthrones.exceptions.NotFoundException;
//import home.ua.gameofthrones.service.CharacterService;
//import home.ua.gameofthrones.service.Impl.CharacterServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
//public class CharacterControllerTest {
//    @Mock
//    CharacterService characterService;
//    @InjectMocks
//    CharacterServiceImpl characterServiceImpl;
//    @Autowired
//    private WebApplicationContext wac;
//    private MockMvc mockMvc;
//
//    @Before
//    public void setup() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(wac)
//                .build();
//    }
//    @Test(expected = NotFoundException.class)
//    public void testFindTheGreatestFromAllData() {
//        when(characterService.collectDataAndSaveIntoDatabase("Name")).thenThrow(new NotFoundException("No such element"));
//        try {
//            mockMvc.perform(get("/characters/{characterName}", "Name"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//}
