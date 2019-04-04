package home.ua.gameofthrones.service;

import home.ua.gameofthrones.domain.Character;
import home.ua.gameofthrones.domain.House;
import home.ua.gameofthrones.entity.CharacterEntity;
import home.ua.gameofthrones.entity.HouseEntity;
import home.ua.gameofthrones.exceptions.AlreadyExistsException;
import home.ua.gameofthrones.repository.CharacterRepository;
import home.ua.gameofthrones.repository.HouseRepository;
import home.ua.gameofthrones.utils.ObjectMapperUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@Service
public class CharacterService {
  @Autowired
    CharacterRepository characterRepository;
  /*  @Autowired
    HouseRepository houseRepository;*/
    @Autowired
    private ObjectMapperUtils modelMapper;
    public void save(String character){
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(httpClient);
            RestTemplate restTemplate = new RestTemplate(requestFactory);
        System.out.println("Fuckin': "+characterRepository.getByName(character));
            if(characterRepository.existsByName(character)) {
                throw new AlreadyExistsException("This character is already in database!!!");
            }

                Object[] result = restTemplate.getForObject("https://anapioficeandfire.com/api/characters/?name=" + character, Character[].class);
                Character characterDTO = (Character) result[0];
           /* String[]urls = characterDTO.getUrl().split("/");
            int size = urls.length;
            System.out.println("id:" +urls[size-1]);*/
                String[] allegiances = characterDTO.getAllegiances();
             /*   House houseDTO = null;
                if(houseRepository.existsByName(allegiances[0])){
                   houseDTO = modelMapper.map(houseRepository.getByName(allegiances[0]),House.class);
                }
               else{  houseDTO = restTemplate.getForObject(allegiances[0], House.class);
                    String[] sworn1 = houseDTO.getSwornMembers();
                    String[]sworn = new String[11];
                    if(sworn1.length>10) {
                        for (int i = 0; i < 10; i++) {
                            sworn[i] = sworn1[i];
                        }
                    }
                    else{
                        sworn = sworn1;
                    }
                    houseDTO.setSwornMembers(sworn);*/
                    House houseDTO = restTemplate.getForObject(allegiances[0], House.class);
                         String[] sworn = houseDTO.getSwornMembers();
                    System.out.println("SWORN: "+sworn[0]);
           //    houseRepository.save(modelMapper.map(houseDTO, HouseEntity.class));//}
       // String[] sworn = (String[]) Arrays.stream(houseDTO.getSwornMembers()).limit(40).toArray();
                Random random = new Random();
                Character foundCharacter = restTemplate.getForObject(sworn[random.nextInt(sworn.length)], Character.class);
                while ((foundCharacter.getUrl().equals(characterDTO.getUrl()))||(foundCharacter.getDied() != "")) {
                    foundCharacter = restTemplate.getForObject(sworn[random.nextInt(sworn.length)], Character.class);
                }//  characterDTO.setId(Long.parseLong(urls[size-1]));
                characterDTO.setHouse(houseDTO.getName());
                System.out.println("Found Character: " + foundCharacter);
                characterDTO.setCharacter(foundCharacter.getName());
                String[] urls = foundCharacter.getUrl().split("/");
                int size = urls.length;
                System.out.println("id of found :" + urls[size - 1]);
                characterDTO.setCharacterID(Long.parseLong(urls[size - 1]));
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
                characterRepository.save(modelMapper.map(characterDTO, CharacterEntity.class));
              System.out.println("Fuckin id': "+characterRepository.getById(0L));

    }
    public Character getByName(String character){

        return modelMapper.map(characterRepository.getByName(character),Character.class);
    }

    /*public int save1(String character){
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

    }*/

    public Page<CharacterEntity> getUsersByPage(Pageable pageable) {
        Page<CharacterEntity> userEntities =
                characterRepository.findAll(pageable);
        // page = 0
        // size = 10
        return  userEntities;
    }


}
