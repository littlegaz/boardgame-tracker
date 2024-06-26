package project.boardgames.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.boardgames.model.BoardgameScore;

import java.util.List;

public interface BoardgameScoreRepository extends JpaRepository<BoardgameScore, Integer> {
    List<BoardgameScore> findByBoardgameId(int boardgameId);
}
