package home.ua.gameofthrones.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class House {

    private String name;
    private String[] swornMembers;


    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                ", swornMembers=" + Arrays.toString(swornMembers) +
                '}';
    }
}
