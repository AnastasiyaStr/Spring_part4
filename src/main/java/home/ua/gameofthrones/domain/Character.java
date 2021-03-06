package home.ua.gameofthrones.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
@Getter@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {
    private Long id;
    private String url;
    private String name;
    private String gender;
    private String[] allegiances;
    private String died;
    private String father;
    private String mother;
    private String spouse;
    private String House;
    private String character;
    private Long characterID;
    private String relationship;

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", allegiances=" + Arrays.toString(allegiances) +
                ", died='" + died + '\'' +
                ", House='" + House + '\'' +
                ", character=" + character +
                '}';
    }
}
