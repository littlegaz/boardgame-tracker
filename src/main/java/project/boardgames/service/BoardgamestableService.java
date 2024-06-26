package project.boardgames.service;/* This specifies that the class BoardgamestableService belongs to projects.boardgames.service*/

import org.springframework.beans.factory.annotation.Autowired;/* This is used for automatic dependency injection*/
import org.springframework.stereotype.Service;/* this indicates that this class is a service component in the spring framework*/
import project.boardgames.model.Boardgamestable; /* links to the project.boardgames.model.Boardgamestable */
import project.boardgames.repository.BoardgamestableRepository;/* links to the repository that contains the CRUD operations */

import java.util.List;
import java.util.Optional;/* these two are java collections for managing lists of objects */

@Service /*marks this class as a spring service component. Typically used to encapsulate business logic */
public class BoardgamestableService {

    @Autowired /* @Autowired injects BoardgamestableRepository into this class*/
    private BoardgamestableRepository boardgamestableRepository;

    public Boardgamestable save(Boardgamestable boardgamestable) {
        return boardgamestableRepository.save(boardgamestable);
    }/*saves or updates a board game entity into the database */

    public List<Boardgamestable> getAllBoardgames() {
        return boardgamestableRepository.findAll();
    }/* retrieves all board game entities from the database*/

    public List<Boardgamestable> findByNameContaining(String name) {
        return boardgamestableRepository.findByNameContainingIgnoreCase(name);
    }/* search for boardgames whose name contains the specified string*/

    public void deleteById(int id) {
        boardgamestableRepository.deleteById(id);
    }/* deletes a boardgame from the database by ID*/

    public Optional<Boardgamestable> findById(int id) {
        return boardgamestableRepository.findById(id);
    }/* retrieves a board game from the database by ID. Optional wrapped to handle nulls*/
}/* These are typical CRUD methods of Create, Read, Update, Delete that interact with the BoardgamestableRepository*/