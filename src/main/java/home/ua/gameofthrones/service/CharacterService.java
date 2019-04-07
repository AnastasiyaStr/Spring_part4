package home.ua.gameofthrones.service;

public interface CharacterService {

    Long collectDataAndSaveIntoDatabase(String character);
    String returnCharacterAndFellowInfoById(Long id);
}
