package home.ua.gameofthrones.repository;

import home.ua.gameofthrones.entity.CharacterEntity;
import home.ua.gameofthrones.entity.HouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<HouseEntity, Long> {
  boolean existsByName(String name);
  HouseEntity getByName(String name);
}
