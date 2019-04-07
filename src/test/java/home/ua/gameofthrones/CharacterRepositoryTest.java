package home.ua.gameofthrones;

import home.ua.gameofthrones.entity.CharacterEntity;
import home.ua.gameofthrones.repository.CharacterRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CharacterRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CharacterRepository characterRepository;

    @Test
    public void CharacterRepositoryIntegrationTest() {
        CharacterEntity alex = new CharacterEntity();
        alex.setId(1L);
        alex.setName("Alex");
        entityManager.persist(alex);
        entityManager.flush();
        CharacterEntity found = characterRepository.getByName(alex.getName());
        assert (found.getName()).equals(alex.getName());
    }
}
