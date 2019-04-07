package home.ua.gameofthrones;

import home.ua.gameofthrones.domain.Character;
import home.ua.gameofthrones.service.FellowAliveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FellowAliveServiceTest {
    @Autowired
    FellowAliveService fellowAliveService;



    @Test
    public void givenTwoCharactersWhenFindRelationshipMIsCalledRightRelationIsReturned() {

        Character entered = new Character();
        entered.setUrl("https://anapioficeandfire.com/api/characters/232");
        entered.setSpouse("https://anapioficeandfire.com/api/characters/339");
        Character found = new Character();
        found.setUrl("https://anapioficeandfire.com/api/characters/339");
        found.setSpouse("https://anapioficeandfire.com/api/characters/232");
        assertEquals(fellowAliveService.findRelationship(entered,found),"spouse");

    }

    @Test
    public void givenUrlWhenParseIdFromUrlShouldReturnLongId() {
       String url =  "https://anapioficeandfire.com/api/characters/232";
      Long id = fellowAliveService.parseIdFromUrl(url);
        assertEquals(232, (long) id);

    }

    @Test
    public void givenUnexistingNameWhenCallThirdPartyIsCalledShouldReturnCharacter() {
        Character found = fellowAliveService.callThirdPartyAPI("Jon Snow");
        assertNotNull(found);

    }

    @Test
    public void givenUnexistingNameWhenCallThirdPartyIsCalledShouldReturnNull() {
        Character found = fellowAliveService.callThirdPartyAPI("Jon");
        assertNull(found);
    }


}
