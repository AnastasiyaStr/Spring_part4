//package home.ua.gameofthrones;
//
//import home.ua.gameofthrones.domain.Character;
//import home.ua.gameofthrones.entity.CharacterEntity;
//import home.ua.gameofthrones.repository.CharacterRepository;
//import home.ua.gameofthrones.service.Impl.CharacterService;
//
//import home.ua.gameofthrones.utils.ObjectMapperUtils;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//public class ServiceIntegrationTest {
//
//    @TestConfiguration
//    static class EmployeeServiceImplTestContextConfiguration {
//
//        @Bean
//        public CharacterService characterService() {
//            return new CharacterService();
//        }
//   /*     @Bean
//        public ObjectMapperUtils modelMapper() {
//            return  modelMapper();
//        }*/
//
//    }
//
//
//    @Autowired
//    private CharacterService characterService;
//    @MockBean
//    private ObjectMapperUtils modelMapper;
//    @MockBean
//    private CharacterRepository characterRepository;
//
//    @Before
//    public void setUp() {
//        CharacterEntity alex = new CharacterEntity();
//        alex.setName("Alex");
//
//        Mockito.when(characterRepository.getByName(alex.getName()))
//                .thenReturn(alex);
//    }
//
//    @Test
//    public void whenValidName_thenEmployeeShouldBeFound() {
//       // Pageable companies = Mockito.mock(Pageable.class);
//
//       Character found = characterService.getByName("Alex");
//
//        assert found.getName().equals("Alex");
//    }
//    // write test cases here
//}


