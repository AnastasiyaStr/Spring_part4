package home.ua.gameofthrones.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "charact")
//Response
public class CharacterEntity /*extends BaseEntity*/{

    @Id
    private Long id;
    @Column(name = "url")
    private String url;
    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "house")
    private String house;

    //fellow alive person
    @Column(name = "_character")
    private String character;
    @Column(name = "_characterID")
    private String characterID;
    @Column(name = "relationship")//enum
    private String relationship;
    //private humanReadableDescription
}
