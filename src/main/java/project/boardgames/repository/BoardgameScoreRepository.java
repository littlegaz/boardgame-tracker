package project.boardgames.repository; /* states where this package belongs */

import org.springframework.data.jpa.repository.JpaRepository; /* an interface provided by spring data JPA that provides methods for performing CRUD operations*/
import project.boardgames.model.BoardgameScore; /*  links to a model class */

import java.util.List;

/* this means the entity type, boardgameScore, and the primary key in an integer */
public interface BoardgameScoreRepository extends JpaRepository<BoardgameScore, Integer> {
    List<BoardgameScore> findByBoardgameId(int boardgameId);
}/* this method will generate a query to find all BoardgameScore entries that have a boardgameId */
