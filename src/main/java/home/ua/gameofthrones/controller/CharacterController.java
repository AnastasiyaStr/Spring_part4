package home.ua.gameofthrones.controller;

import home.ua.gameofthrones.service.Impl.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("characters")
public class CharacterController {
@Autowired
CharacterServiceImpl characterServiceImpl;

 /*   @GetMapping("hello")
    public ResponseEntity<?> returnHello() {
        return new ResponseEntity<>("Hello world!!!",HttpStatus.CREATED);
    }*/


    @PostMapping("{characterName}")//Add validation!!!!
    public ResponseEntity<?> findCharacterByName1(@PathVariable("characterName")@Valid @NotNull String name) {
            characterServiceImpl.collectDataAndSaveIntoDatabase(name);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }
    /*@GetMapping//Add validation!!!!
    public ResponseEntity<?> findCharacters() {
        return new ResponseEntity<>( characterServiceImpl.findAll(), HttpStatus.OK);

    }*/
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

    @GetMapping // /users/page?page=0&size=20&sort=
    public ResponseEntity<?> getUsersByPage(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(characterServiceImpl.getUsersByPage(pageable), HttpStatus.OK);
    }


    @GetMapping("{characterId}") // /users/page?page=0&size=20&sort=
    public ResponseEntity<?> getUsersByPage(@PathVariable("characterId")@Valid @NotNull Long id) {
        return new ResponseEntity<>(characterServiceImpl.returnCharacterAndFellowInfoById(id),HttpStatus.OK);
    }

}
