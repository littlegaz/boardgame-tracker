package project.boardgames.repository; /* specifies that the repository belongs to project.boardgams.repository*/

import org.springframework.data.jpa.repository.JpaRepository; /* this is an interface provided by spring data jpa that provides methods for performing CRUD operations*/
import project.boardgames.model.Boardgamestable; /* linking to a model class boardgamestable */

import java.util.List; 

/* this means the entity type, boardgamestable, and the primary key in an integer */
public interface BoardgamestableRepository extends JpaRepository<Boardgamestable, Integer> {
    List<Boardgamestable> findByNameContainingIgnoreCase(String name);
}/* this method will automatically generate a query to find all boardgamestable entities where there is a name in the name column*/