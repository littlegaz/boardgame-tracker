package project.boardgames.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return "redirect:/?successMessage=" + boardgamestable.getName() + " has been added successfully!";
    }

    @GetMapping("/api/boardgames/all")
    public @ResponseBody List<Boardgamestable> getAllBoardgames() {
        return boardgamestableService.getAllBoardgames();
    }

    @PostMapping("/api/boardgames/update/{id}")
    public String updateBoardgame(@PathVariable int id, @ModelAttribute Boardgamestable updatedBoardgame) {
        updatedBoardgame.setId(id);
        boardgamestableService.save(updatedBoardgame);
        return "redirect:/boardgame/" + id + "?successMessage=" + updatedBoardgame.getName() + " has been updated successfully!";
    }

    @DeleteMapping("/api/boardgames/delete/{id}")
    public ResponseEntity<Void> deleteBoardgame(@PathVariable int id) {
        if (boardgamestableService.findById(id).isPresent()) {
            boardgamestableService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public String homePage(Model model,
                           @RequestParam Optional<String> numPlayers,
                           @RequestParam Optional<String> time,
                           @RequestParam Optional<String> difficulty,
                           @RequestParam Optional<String> searchName,
                           @RequestParam Optional<String> successMessage) {
        List<Boardgamestable> boardgames;

        if (searchName.isPresent() && !searchName.get().isEmpty()) {
            boardgames = boardgamestableService.findByNameContaining(searchName.get());
        } else {
            boardgames = boardgamestableService.getAllBoardgames();
        }

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
        successMessage.ifPresent(msg -> model.addAttribute("successMessage", msg));
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