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
    public String addBoardgame(@ModelAttribute Boardgamestable boardgamestable) {
        boardgamestableService.save(boardgamestable);
        return "redirect:/";
    }

    @GetMapping("/api/boardgames/all")
    public @ResponseBody List<Boardgamestable> getAllBoardgames() {
        return boardgamestableService.getAllBoardgames();
    }

    @PostMapping("/api/boardgames/update/{id}")
    public String updateBoardgame(@PathVariable int id, @ModelAttribute Boardgamestable updatedBoardgame) {
    updatedBoardgame.setId(id);
    boardgamestableService.save(updatedBoardgame);
    return "redirect:/boardgame/" + id;
}

    @DeleteMapping("/api/boardgames/delete/{id}")
    public @ResponseBody void deleteBoardgame(@PathVariable int id) {
        boardgamestableService.deleteById(id);
    }

    @GetMapping("/")
    public String homePage(Model model,
                           @RequestParam Optional<String> numPlayers,
                           @RequestParam Optional<String> time,
                           @RequestParam Optional<String> difficulty) {
        List<Boardgamestable> boardgames = boardgamestableService.getAllBoardgames();

        // Apply filters
        if (numPlayers.isPresent() && !numPlayers.get().isEmpty()) {
            int numPlayersInt = Integer.parseInt(numPlayers.get());
            boardgames = boardgames.stream().filter(bg -> bg.getMinPlayers() <= numPlayersInt && bg.getMaxPlayers() >= numPlayersInt).toList();
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