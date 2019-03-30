package home.ua.gameofthrones;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Character {


    private String name;
    private String gender;
    private String[] allegiances;
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

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
