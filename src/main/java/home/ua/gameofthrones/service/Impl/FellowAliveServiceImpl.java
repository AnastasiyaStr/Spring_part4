package home.ua.gameofthrones.service.Impl;

import home.ua.gameofthrones.domain.Character;
import home.ua.gameofthrones.domain.House;
import home.ua.gameofthrones.exceptions.RestTemplateResponseErrorHandler;
import home.ua.gameofthrones.service.FellowAliveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
@Service
public class FellowAliveServiceImpl implements FellowAliveService {
 //   @Autowired
    private final RestTemplate restTemplate;
    @Autowired
    public FellowAliveServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }
    @Override
    public Character callThirdPartyAPI(String character){
        Character characterDTO = null;

        try {
            Character[] result = restTemplate.getForObject("https://anapioficeandfire.com/api/characters/?name=" + character, Character[].class);
            characterDTO = result[0];
       }
            catch(ArrayIndexOutOfBoundsException e){return null;}
            characterDTO.setId(parseIdFromUrl(characterDTO.getUrl()));
            String[] allegiances = characterDTO.getAllegiances();
            House houseDTO = restTemplate.getForObject(allegiances[0], House.class);
            String[] sworn = houseDTO.getSwornMembers();
            System.out.println("SWORN: " + sworn[0]);
            Random random = new Random();
            Character foundCharacter = restTemplate.getForObject(sworn[random.nextInt(sworn.length)], Character.class);
            while ((foundCharacter.getUrl().equals(characterDTO.getUrl())) || (foundCharacter.getDied() != "")) {
                foundCharacter = restTemplate.getForObject(sworn[random.nextInt(sworn.length)], Character.class);
            }
            //окремий метод
            characterDTO.setHouse(houseDTO.getName());
            System.out.println("Found Character: " + foundCharacter);
            characterDTO.setCharacter(foundCharacter.getName());
           /* String[] urls = foundCharacter.getUrl().split("/");
            int size = urls.length;
            System.out.println("id of found :" + urls[size - 1]);
            characterDTO.setCharacterID(Long.parseLong(urls[size - 1]));*/

            // if(!foundCharacter.getUrl().equals("")) {
           /* if (foundCharacter.getUrl().equals(characterDTO.getFather())) {
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
            }*/
           characterDTO.setRelationship(findRelationship(characterDTO,foundCharacter));
            characterDTO.setCharacterID(parseIdFromUrl(foundCharacter.getUrl()));
            return characterDTO;
        }

        public Long parseIdFromUrl(String url){
            String[] urls = url.split("/");
            int size = urls.length;
            System.out.println("id of found :" +urls[size-1]);
            return Long.parseLong(urls[size-1]);

        }
        public String findRelationship(Character enteredCharacter, Character foundCharacter){
            if (foundCharacter.getUrl().equals(enteredCharacter.getSpouse())) {
                return "spouse";
            }
            if (foundCharacter.getUrl().equals(enteredCharacter.getFather())) {
                return "father";
            }
            if (foundCharacter.getUrl().equals(enteredCharacter.getMother())) {
                return "mother";
            }

            // }

                return "fellow";

        }
    }
