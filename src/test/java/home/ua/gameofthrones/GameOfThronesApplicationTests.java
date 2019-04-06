package home.ua.gameofthrones;

import home.ua.gameofthrones.entity.CharacterEntity;
import home.ua.gameofthrones.repository.CharacterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataJpaTest
public class GameOfThronesApplicationTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CharacterRepository characterRepository;

    /* @Test
     public void contextLoads() {
     }*/
    @Test
    public void EmployeeRepositoryIntegrationTest() {
// given
        CharacterEntity alex = new CharacterEntity();
        alex.setName("Alex");
        entityManager.persist(alex);
        entityManager.flush();

        // when
        CharacterEntity found = characterRepository.getByName(alex.getName());

        // then
        assert (found.getName()).equals(alex.getName());
    }
}
