package home.ua.gameofthrones.service.Impl;

import home.ua.gameofthrones.domain.Character;
import home.ua.gameofthrones.domain.House;
import home.ua.gameofthrones.entity.CharacterEntity;
import home.ua.gameofthrones.exceptions.NotFoundException;
import home.ua.gameofthrones.repository.CharacterRepository;
import home.ua.gameofthrones.service.CharacterService;
import home.ua.gameofthrones.service.FellowAliveService;
import home.ua.gameofthrones.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Service
public class CharacterServiceImpl implements CharacterService {
    @Autowired
    CharacterRepository characterRepository;


    @Autowired
    private FellowAliveService fellowAliveService;

    public Long collectDataAndSaveIntoDatabase(String character) {

        Character characterFound = fellowAliveService.callThirdPartyAPI(character);
        if (characterFound == null) throw new NotFoundException("Specified character not found!!!");
        CharacterEntity characterEntity = characterRepository.save(ObjectMapperUtils.map(characterFound, CharacterEntity.class));//пов ен
        return characterEntity.getId();


    }


    public Page<CharacterEntity> getUsersByPage(Pageable pageable) {
        Page<CharacterEntity> userEntities =
                characterRepository.findAll(pageable);
        return userEntities;
    }

    @Override
    public String returnCharacterAndFellowInfoById(Long id) {
        CharacterEntity characterEntity = characterRepository.getById(id);
        if (characterEntity == null) throw new NotFoundException("No character with such id!!!");
        return "Character" + characterEntity.getName() + "(id: " + characterEntity.getId() + ") has a " + characterEntity.getRelationship()
                + " " + characterEntity.getCharacter() + "(who is not dead yet) with id( " + characterEntity.getCharacterID() + " ) from house: " + characterEntity.getHouse();
    }
}
