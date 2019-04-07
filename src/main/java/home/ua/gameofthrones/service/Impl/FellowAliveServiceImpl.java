package home.ua.gameofthrones.service.Impl;

import home.ua.gameofthrones.domain.Character;
import home.ua.gameofthrones.domain.House;
import home.ua.gameofthrones.exceptions.NotFoundException;
import home.ua.gameofthrones.exceptions.RestTemplateResponseErrorHandler;
import home.ua.gameofthrones.service.FellowAliveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class FellowAliveServiceImpl implements FellowAliveService {

    private final RestTemplate restTemplate;

    @Autowired
    public FellowAliveServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    @Override
    public Character callThirdPartyAPI(String character) {
        Character characterDTO;

        try {
            Character[] result = restTemplate.getForObject("https://anapioficeandfire.com/api/characters/?name=" + character, Character[].class);
            characterDTO = result[0];
        } catch (Exception e) {
            return null;
        }
        characterDTO.setId(parseIdFromUrl(characterDTO.getUrl()));
        String[] allegiances = characterDTO.getAllegiances();
        House houseDTO = restTemplate.getForObject(allegiances[0], House.class);
        String[] sworn = houseDTO.getSwornMembers();
        System.out.println("SWORN: " + sworn[0]);
        Random random = new Random();
        Character foundCharacter = restTemplate.getForObject(sworn[random.nextInt(sworn.length)], Character.class);
        while ((foundCharacter.getUrl().equals(characterDTO.getUrl())) || (foundCharacter.getDied() != "")) {
            try {
                foundCharacter = restTemplate.getForObject(sworn[random.nextInt(sworn.length)], Character.class);
            } catch (Exception e) {
                throw new NotFoundException("Could not reach API...");
            }
        }

        characterDTO.setHouse(houseDTO.getName());
        System.out.println("Found Character: " + foundCharacter);
        characterDTO.setCharacter(foundCharacter.getName());
        characterDTO.setRelationship(findRelationship(characterDTO, foundCharacter));
        characterDTO.setCharacterID(parseIdFromUrl(foundCharacter.getUrl()));
        return characterDTO;
    }

    public Long parseIdFromUrl(String url) {
        String[] urls = url.split("/");
        int size = urls.length;
        System.out.println("id of found :" + urls[size - 1]);
        return Long.parseLong(urls[size - 1]);

    }

    public String findRelationship(Character enteredCharacter, Character foundCharacter) {
        if (foundCharacter.getUrl().equals(enteredCharacter.getSpouse())) {
            return "spouse";
        }
        if (foundCharacter.getUrl().equals(enteredCharacter.getFather())) {
            return "father";
        }
        if (foundCharacter.getUrl().equals(enteredCharacter.getMother())) {
            return "mother";
        }

        return "fellow";
    }
}
