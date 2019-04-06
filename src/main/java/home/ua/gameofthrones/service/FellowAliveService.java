package home.ua.gameofthrones.service;

import home.ua.gameofthrones.domain.Character;
import org.springframework.stereotype.Service;


public interface FellowAliveService {

    public Character callThirdPartyAPI(String character);
}
