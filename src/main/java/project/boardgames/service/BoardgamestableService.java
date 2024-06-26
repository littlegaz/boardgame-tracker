package project.boardgames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.boardgames.model.Boardgamestable;
import project.boardgames.repository.BoardgamestableRepository;

import java.util.List;
import java.util.Optional;

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

    public List<Boardgamestable> findByNameContaining(String name) {
        return boardgamestableRepository.findByNameContainingIgnoreCase(name);
    }

    public void deleteById(int id) {
        boardgamestableRepository.deleteById(id);
    }

    public Optional<Boardgamestable> findById(int id) {
        return boardgamestableRepository.findById(id);
    }
}