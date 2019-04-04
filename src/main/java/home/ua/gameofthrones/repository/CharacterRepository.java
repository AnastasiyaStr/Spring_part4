package home.ua.gameofthrones.repository;


import home.ua.gameofthrones.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
    boolean existsByName(String name);
    CharacterEntity getByName(String name);
    CharacterEntity  getById(Long id);
}
