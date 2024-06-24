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
    public String homePage(Model model,
                           @RequestParam Optional<String> minPlayers,
                           @RequestParam Optional<String> maxPlayers,
                           @RequestParam Optional<String> time,
                           @RequestParam Optional<String> difficulty) {
        List<Boardgamestable> boardgames = boardgamestableService.getAllBoardgames();

        // Apply filters
        if (minPlayers.isPresent() && !minPlayers.get().isEmpty()) {
            int minPlayersInt = Integer.parseInt(minPlayers.get());
            boardgames = boardgames.stream().filter(bg -> bg.getMinPlayers() >= minPlayersInt).toList();
        }
        if (maxPlayers.isPresent() && !maxPlayers.get().isEmpty()) {
            int maxPlayersInt = Integer.parseInt(maxPlayers.get());
            boardgames = boardgames.stream().filter(bg -> bg.getMaxPlayers() <= maxPlayersInt).toList();
        }
        if (time.isPresent() && !time.get().isEmpty()) {
            switch (time.get()) {
                case "Under 30 Minutes":
                    boardgames = boardgames.stream().filter(bg -> bg.getTime() < 30).toList();
                    break;
                case "Under 60 Minutes":
                    boardgames = boardgames.stream().filter(bg -> bg.getTime() < 60).toList();
                    break;
                case "Over 60 Minutes":
                    boardgames = boardgames.stream().filter(bg -> bg.getTime() >= 60).toList();
                    break;
            }
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