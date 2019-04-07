package home.ua.gameofthrones.service;

import home.ua.gameofthrones.domain.Character;
import org.springframework.stereotype.Service;


public interface FellowAliveService {
     Long parseIdFromUrl(String url);
     Character callThirdPartyAPI(String character);
     String findRelationship(Character enteredCharacter, Character foundCharacter);
}
