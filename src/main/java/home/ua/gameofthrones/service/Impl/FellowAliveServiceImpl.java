package home.ua.gameofthrones.service.Impl;

import home.ua.gameofthrones.domain.Character;
import home.ua.gameofthrones.domain.House;
import home.ua.gameofthrones.service.FellowAliveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
@Service
public class FellowAliveServiceImpl implements FellowAliveService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Character callThirdPartyAPI(String character){
            Character[] result = restTemplate.getForObject("https://anapioficeandfire.com/api/characters/?name=" + character, Character[].class);


            Character characterDTO = result[0];

            characterDTO.setId(parseIdFromUrl(characterDTO.getUrl()));

            String[] allegiances = characterDTO.getAllegiances();

            House houseDTO = restTemplate.getForObject(allegiances[0], House.class);
            String[] sworn = houseDTO.getSwornMembers();
            System.out.println("SWORN: " + sworn[0]);

            Random random = new Random();


            Character foundCharacter = restTemplate.getForObject(sworn[random.nextInt(sworn.length)], Character.class);
            while ((foundCharacter.getUrl().equals(characterDTO.getUrl())) || (foundCharacter.getDied() != "")) {
                foundCharacter = restTemplate.getForObject(sworn[random.nextInt(sworn.length)], Character.class);
            }//  characterDTO.setId(Long.parseLong(urls[size-1]));

            //окремий метод
            characterDTO.setHouse(houseDTO.getName());
            System.out.println("Found Character: " + foundCharacter);


            characterDTO.setCharacter(foundCharacter.getName());
           /* String[] urls = foundCharacter.getUrl().split("/");
            int size = urls.length;
            System.out.println("id of found :" + urls[size - 1]);
            characterDTO.setCharacterID(Long.parseLong(urls[size - 1]));*/

            // if(!foundCharacter.getUrl().equals("")) {
            if (foundCharacter.getUrl().equals(characterDTO.getFather())) {
                characterDTO.setRelationship("father of");
            }
            if (foundCharacter.getUrl().equals(characterDTO.getMother())) {
                characterDTO.setRelationship("mother of");
            }
            if (foundCharacter.getUrl().equals(characterDTO.getSpouse())) {
                characterDTO.setRelationship("spouse of");
            }
            // }
            if (characterDTO.getRelationship() == null) {
                characterDTO.setRelationship("either way acquainted with");
            }
            characterDTO.setCharacterID(parseIdFromUrl(foundCharacter.getUrl()));
            return characterDTO;
        }

        public Long parseIdFromUrl(String url){
            String[] urls = url.split("/");
            int size = urls.length;
            System.out.println("id of found :" +urls[size-1]);
            return Long.parseLong(urls[size-1]);

        }
    }
