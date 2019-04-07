//package home.ua.gameofthrones;
//
//
//import home.ua.gameofthrones.controller.CharacterController;
//import home.ua.gameofthrones.domain.Character;
//import home.ua.gameofthrones.service.Impl.CharacterServiceImpl;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.List;
//
//
//import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
//import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(CharacterController.class)
//public class ControllerIntegrationTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private CharacterServiceImpl service;
//
//    @Test
//    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
//            throws Exception {
//
//        Character alex = new Character();
//     alex.setName("alex");
//        List<Character> allEmployees = Arrays.asList(alex);
//        given(service.findAll()).willReturn(allEmployees);
//        mvc.perform(get("/characters")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)));
//    }
//
//}
