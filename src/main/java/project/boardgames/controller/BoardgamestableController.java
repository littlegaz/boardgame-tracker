package project.boardgames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import project.boardgames.model.Boardgamestable;
import project.boardgames.service.BoardgamestableService;

import java.util.List;

@Controller
public class BoardgamestableController {

    @Autowired
    private BoardgamestableService boardgamestableService;

    @PostMapping("/api/boardgames/add")
    public @ResponseBody Boardgamestable addBoardgame(@RequestBody Boardgamestable boardgamestable) {
        return boardgamestableService.save(boardgamestable);
    }

    @GetMapping("/api/boardgames/all")
    public @ResponseBody List<Boardgamestable> getAllBoardgames() {
        return boardgamestableService.getAllBoardgames();
    }

    @PutMapping("/api/boardgames/update/{id}")
    public @ResponseBody Boardgamestable updateBoardgame(@PathVariable int id, @RequestBody Boardgamestable updatedBoardgame) {
        updatedBoardgame.setId(id);
        return boardgamestableService.save(updatedBoardgame);
    }

    @DeleteMapping("/api/boardgames/delete/{id}")
    public @ResponseBody void deleteBoardgame(@PathVariable int id) {
        boardgamestableService.deleteById(id);
    }

    @GetMapping("/")
    public String homePage(Model model) {
        List<Boardgamestable> boardgames = boardgamestableService.getAllBoardgames();
        model.addAttribute("boardgames", boardgames);
        return "index";
    }
}