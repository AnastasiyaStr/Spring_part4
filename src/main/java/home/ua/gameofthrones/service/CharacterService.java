package home.ua.gameofthrones.service;

import home.ua.gameofthrones.domain.Character;
import home.ua.gameofthrones.domain.House;
import home.ua.gameofthrones.entity.CharacterEntity;
import home.ua.gameofthrones.repository.CharacterRepository;
import home.ua.gameofthrones.utils.ObjectMapperUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Random;

@Service
public class CharacterService {
  @Autowired
    CharacterRepository characterRepository;
    @Autowired
    private ObjectMapperUtils modelMapper;
    public int save(String character){
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(httpClient);
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            Object[] result = restTemplate.getForObject("https://anapioficeandfire.com/api/characters/?name="+character, Character[].class);
            Character characterDTO  = (Character) result[0];
            String[]urls = characterDTO.getUrl().split("/");
            int size = urls.length;
            System.out.println("id:" +urls[size-1]);
            String[] allegiances = characterDTO.getAllegiances();
            House houseDTO =  restTemplate.getForObject(allegiances[0], House.class);
            String[]sworn =  houseDTO.getSwornMembers();
            Random random = new Random();
            Character foundCharacter = restTemplate.getForObject(sworn[random.nextInt(sworn.length)], Character.class);
            while(foundCharacter.getDied()!=""){
                foundCharacter = restTemplate.getForObject(sworn[random.nextInt(sworn.length)], Character.class);
            }
            characterDTO.setId(Long.parseLong(urls[size-1]));
            characterDTO.setHouse(houseDTO.getName());
            System.out.println("Found Character: "+foundCharacter);
            characterDTO.setCharacter(foundCharacter.getName());
            urls = foundCharacter.getUrl().split("/");
            System.out.println("id of found :" +urls[size-1]);
            characterDTO.setCharacterID(Long.parseLong(urls[size-1]));
            characterRepository.save(modelMapper.map(characterDTO, CharacterEntity.class));
            return 1;

    }

    public int save1(String character){
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        Object[] result = restTemplate.getForObject("https://anapioficeandfire.com/api/characters/?name="+character, Character[].class);
        Character characterDTO  = (Character) result[0];
        String[]urls = characterDTO.getUrl().split("/");
        int size = urls.length;
        System.out.println("id:" +urls[size-1]);
        String[] allegiances = characterDTO.getAllegiances();
        House houseDTO =  restTemplate.getForObject(allegiances[0], House.class);
        String[]sworn =  houseDTO.getSwornMembers();
        Random random = new Random();
        Character foundCharacter = restTemplate.getForObject(sworn[random.nextInt(sworn.length)], Character.class);
        while(foundCharacter.getDied()!=""){
            foundCharacter = restTemplate.getForObject(sworn[random.nextInt(sworn.length)], Character.class);
        }
        characterDTO.setId(Long.parseLong(urls[size-1]));
        characterDTO.setHouse(houseDTO.getName());
        System.out.println("Found Character: "+foundCharacter);
        characterDTO.setCharacter(foundCharacter.getName());
        urls = foundCharacter.getUrl().split("/");
        System.out.println("id of found :" +urls[size-1]);
        characterDTO.setCharacterID(Long.parseLong(urls[size-1]));
        characterRepository.save(modelMapper.map(characterDTO, CharacterEntity.class));
        return 1;

    }




}
