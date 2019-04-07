package home.ua.gameofthrones;

import home.ua.gameofthrones.domain.Character;
import home.ua.gameofthrones.entity.CharacterEntity;
import home.ua.gameofthrones.repository.CharacterRepository;
import home.ua.gameofthrones.service.CharacterService;
import home.ua.gameofthrones.service.FellowAliveService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CharacterServiceTest {
    @Autowired
    CharacterService characterService;
    @MockBean
    private FellowAliveService fellowAliveService;

    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {
        Character character = new Character();
        character.setId(271L);
        given(fellowAliveService.callThirdPartyAPI("Jon Snow")).willReturn(character);
        Assert.assertEquals(271, (long) characterService.collectDataAndSaveIntoDatabase("Jon Snow"));
    }

    @Autowired
    private CharacterRepository characterRepository;
    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject1() {

        CharacterEntity character = new CharacterEntity();
        character.setId(271L);
        character.setName("Jon Snow");
        character.setCharacterID("888");
        character.setCharacter("Ygritte");
        character.setRelationship("fellow");
        character.setHouse("Winterfell");
        characterRepository.save(character);
        Assert.assertEquals("Character"+character.getName()+"(id: "+character.getId()+") has a " +character.getRelationship()
                +" "+character.getCharacter() +"(who is not dead yet) with id( "+character.getCharacterID()+" ) from house: "+character.getHouse(), characterService.returnCharacterAndFellowInfoById(271L));
    }
}
