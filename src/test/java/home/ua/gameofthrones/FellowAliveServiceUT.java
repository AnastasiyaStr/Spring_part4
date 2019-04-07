package home.ua.gameofthrones;

import home.ua.gameofthrones.domain.Character;
import home.ua.gameofthrones.service.FellowAliveService;
import home.ua.gameofthrones.service.Impl.CharacterServiceImpl;
import home.ua.gameofthrones.service.Impl.FellowAliveServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FellowAliveServiceUT {
    @Autowired
    FellowAliveService fellowAliveService;



    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {

        Character entered = new Character();
        entered.setUrl("https://anapioficeandfire.com/api/characters/232");
        entered.setSpouse("https://anapioficeandfire.com/api/characters/339");
        Character found = new Character();
        found.setUrl("https://anapioficeandfire.com/api/characters/339");
        found.setSpouse("https://anapioficeandfire.com/api/characters/232");
        assertEquals(fellowAliveService.findRelationship(entered,found),"spouse");

    }

    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject1() {
       String url =  "https://anapioficeandfire.com/api/characters/232";
      Long id = fellowAliveService.parseIdFromUrl(url);
        assertEquals(232, (long) id);

    }

    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject2() {
        Character found = fellowAliveService.callThirdPartyAPI("Jon Snow");
        assertNotNull(found);

    }

    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject3() {
        Character found = fellowAliveService.callThirdPartyAPI("Jon");
        assertNull(found);

    }


}
