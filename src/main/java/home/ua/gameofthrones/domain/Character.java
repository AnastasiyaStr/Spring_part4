package home.ua.gameofthrones.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {


    private String name;
    private String gender;
    private String[] allegiances;
    private String died;
    ////////////////////////
    private String House;
    private Character character;
    /////////////////////////



    public String getName() {
        return name;
    }

    public String[] getAllegiances() {
        return allegiances;
    }

    public void setAllegiances(String[] allegiances) {
        this.allegiances = allegiances;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDied() {
        return died;
    }

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
