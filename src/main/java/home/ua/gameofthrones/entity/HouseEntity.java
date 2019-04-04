package home.ua.gameofthrones.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "house")
public class HouseEntity extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "sworn_members", columnDefinition="LONGTEXT")
    private String[] swornMembers;

}
