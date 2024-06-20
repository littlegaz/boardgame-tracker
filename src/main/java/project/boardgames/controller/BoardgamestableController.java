package project.boardgames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.boardgames.model.Boardgamestable;
import project.boardgames.service.BoardgamestableService;

import java.util.List;

@RestController
@RequestMapping("/api/boardgames")
public class BoardgamestableController {

    @Autowired
    private BoardgamestableService boardgamestableService;

    @PostMapping("/add")
    public Boardgamestable addBoardgame(@RequestBody Boardgamestable boardgamestable) {
        return boardgamestableService.save(boardgamestable);
    }

    @GetMapping("/all")
    public List<Boardgamestable> getAllBoardgames() {
        return boardgamestableService.getAllBoardgames();
    }

    

    // Additional CRUD endpoints can be added here
}
