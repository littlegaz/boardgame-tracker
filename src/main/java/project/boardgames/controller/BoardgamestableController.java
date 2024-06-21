package project.boardgames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import project.boardgames.model.Boardgamestable;
import project.boardgames.service.BoardgamestableService;

import java.util.List;
import java.util.Optional;

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
    public String homePage(Model model, @RequestParam Optional<Integer> minPlayers, @RequestParam Optional<Integer> maxPlayers,
                           @RequestParam Optional<Integer> time, @RequestParam Optional<String> difficulty) {
        List<Boardgamestable> boardgames = boardgamestableService.getAllBoardgames();

        // Apply filters
        if (minPlayers.isPresent()) {
            boardgames = boardgames.stream().filter(bg -> bg.getMinPlayers() >= minPlayers.get()).toList();
        }
        if (maxPlayers.isPresent()) {
            boardgames = boardgames.stream().filter(bg -> bg.getMaxPlayers() <= maxPlayers.get()).toList();
        }
        if (time.isPresent()) {
            boardgames = boardgames.stream().filter(bg -> bg.getTime() <= time.get()).toList();
        }
        if (difficulty.isPresent() && !difficulty.get().isEmpty()) {
            boardgames = boardgames.stream().filter(bg -> bg.getDifficulty().equalsIgnoreCase(difficulty.get())).toList();
        }

        model.addAttribute("boardgames", boardgames);
        return "index";
    }

    @GetMapping("/boardgame/{id}")
    public String boardgameDetail(@PathVariable int id, Model model) {
        Optional<Boardgamestable> boardgame = boardgamestableService.findById(id);
        if (boardgame.isPresent()) {
            model.addAttribute("boardgame", boardgame.get());
            return "boardgame-detail";
        } else {
            return "error";
        }
    }
}