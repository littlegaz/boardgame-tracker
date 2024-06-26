package project.boardgames.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.boardgames.model.Boardgamestable;

import java.util.List;

public interface BoardgamestableRepository extends JpaRepository<Boardgamestable, Integer> {
    List<Boardgamestable> findByNameContainingIgnoreCase(String name);
}