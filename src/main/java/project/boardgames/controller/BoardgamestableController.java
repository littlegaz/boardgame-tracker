package project.boardgames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.boardgames.model.Boardgamestable;
import project.boardgames.service.BoardgamestableService;

import java.util.List;

@Controller
public class BoardgamestableController {

    @Autowired
    private BoardgamestableService boardgamestableService;

    @PostMapping("/api/boardgames/add")
    public Boardgamestable addBoardgame(@RequestBody Boardgamestable boardgamestable) {
        return boardgamestableService.save(boardgamestable);
    }

    @GetMapping("/api/boardgames/all")
    public List<Boardgamestable> getAllBoardgames() {
        return boardgamestableService.getAllBoardgames();
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Boardgamestable> boardgames = boardgamestableService.getAllBoardgames();
        model.addAttribute("boardgames", boardgames);
        return "index";
    }

    // Additional CRUD endpoints can be added here
}