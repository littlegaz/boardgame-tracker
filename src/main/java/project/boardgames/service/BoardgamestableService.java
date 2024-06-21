package project.boardgames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.boardgames.model.Boardgamestable;
import project.boardgames.repository.BoardgamestableRepository;

import java.util.List;

@Service
public class BoardgamestableService {

    @Autowired
    private BoardgamestableRepository boardgamestableRepository;

    public Boardgamestable save(Boardgamestable boardgamestable) {
        return boardgamestableRepository.save(boardgamestable);
    }

    public List<Boardgamestable> getAllBoardgames() {
        return boardgamestableRepository.findAll();
    }

    public void deleteById(int id) {
        boardgamestableRepository.deleteById(id);
    }

    // Additional methods for CRUD operations can be added here
}
