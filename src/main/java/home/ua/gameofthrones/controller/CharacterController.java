package home.ua.gameofthrones.controller;

import home.ua.gameofthrones.domain.Character;
import home.ua.gameofthrones.domain.House;
import home.ua.gameofthrones.service.CharacterService;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("characters")
public class CharacterController {
@Autowired
CharacterService characterService;
    @GetMapping("{characterName}")
    public ResponseEntity<?> findCharacterByName1(@PathVariable("characterName") String name) {
            characterService.save(name);


            return new ResponseEntity<>(HttpStatus.CREATED);
    }










  /*  @GetMapping("{characterName}")
    public ResponseEntity<?> findCharacterByName(@PathVariable("characterName") String name) {

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        Object[] result = restTemplate.getForObject("https://anapioficeandfire.com/api/characters/?name=daenerys targaryen",Character[].class);
        String allegiance = ((Character)result[0]).getAllegiances()[0];
        System.out.println("Allegiances: "+((Character)result[0]).getAllegiances()[0]);
        Object result1 = restTemplate.getForObject(allegiance, House.class);
        System.out.println(result);
        String swornMember = ((House) result1).getSwornMembers()[0];
        result1 = restTemplate.getForObject(swornMember, Character.class);
        System.out.println("Result 1: "+result1);
            return new ResponseEntity<>(result1,HttpStatus.ACCEPTED);

    }*/



}
