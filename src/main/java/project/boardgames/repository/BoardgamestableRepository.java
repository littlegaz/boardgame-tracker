package project.boardgames.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.boardgames.model.Boardgamestable;

public interface BoardgamestableRepository extends JpaRepository<Boardgamestable, Integer> {
}