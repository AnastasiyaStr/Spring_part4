package home.ua.gameofthrones.controller;

import home.ua.gameofthrones.service.Impl.CharacterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("characters")
public class CharacterController {
    @Autowired
    CharacterServiceImpl characterServiceImpl;

    @PostMapping("{characterName}")
    public ResponseEntity<?> findCharacterByName1(@PathVariable("characterName") @Valid @NotNull String name) {
        characterServiceImpl.collectDataAndSaveIntoDatabase(name);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getUsersByPage(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(characterServiceImpl.getUsersByPage(pageable), HttpStatus.OK);
    }

    @GetMapping("{characterId}")
    public ResponseEntity<?> getUsersByPage(@PathVariable("characterId") @Valid @NotNull Long id) {
        return new ResponseEntity<>(characterServiceImpl.returnCharacterAndFellowInfoById(id), HttpStatus.OK);
    }

}
