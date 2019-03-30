package home.ua.gameofthrones.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class House {

    private String name;
    private String[] swornMembers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSwornMembers() {
        return swornMembers;
    }

    public void setSwornMembers(String[] swornMembers) {
        this.swornMembers = swornMembers;
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", swornMembers=" + Arrays.toString(swornMembers) +
                '}';
    }
}
