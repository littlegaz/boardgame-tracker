package project.boardgames.controller; /* this is a spring MVC that handles HTTP requests related to boardgamestable objects. it uses annotations to map HTTP requeststo handler methods, inject dependancies and process data */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.boardgames.model.Boardgamestable;
import project.boardgames.model.BoardgameScore;
import project.boardgames.service.BoardgamestableService;
import project.boardgames.service.BoardgameScoreService;

import java.util.List;
import java.util.Optional;

/* above are various spring annotations and classes */

@Controller /* this marks the class as the web controller that handles the HTTP requests */
public class BoardgamestableController {

    @Autowired /*this injects the BoardgamestableService bean enabling the controller to use its methods */
    private BoardgamestableService boardgamestableService;

    @Autowired
    private BoardgameScoreService boardgameScoreService;

    @PostMapping("/api/boardgames/add")
    public String addBoardgame(@ModelAttribute Boardgamestable boardgamestable) {
        boardgamestableService.save(boardgamestable);
        return "redirect:/?successMessage=" + boardgamestable.getName() + " has been added successfully!";
    }/*this adds a boardgame, sends the data to boardgametable, saves it there and redirects back to the homepage afterwards */

    @GetMapping("/api/boardgames/all")
    public @ResponseBody List<Boardgamestable> getAllBoardgames() {
        return boardgamestableService.getAllBoardgames();
    }/*this gets all the games and returns all the game data back to the front page */

    @PostMapping("/api/boardgames/update/{id}")
    public String updateBoardgame(@PathVariable int id, @ModelAttribute Boardgamestable updatedBoardgame) {
        updatedBoardgame.setId(id);
        boardgamestableService.save(updatedBoardgame);
        return "redirect:/boardgame/" + id + "?successMessage=" + updatedBoardgame.getName() + " has been updated successfully!";
    }/*this updates a boardgame using the primary key to find and update the game. This will then redirect back to the boardgame-detail page */

    @DeleteMapping("/api/boardgames/delete/{id}")
    public ResponseEntity<Void> deleteBoardgame(@PathVariable int id) {
        if (boardgamestableService.findById(id).isPresent()) {
            boardgamestableService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }/*this will delete a game by linked id to remove it from the database if the id is present */

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
        }/*this applies filters to game details and gets the board games that meet the filters */

        model.addAttribute("boardgames", boardgames);
        successMessage.ifPresent(msg -> model.addAttribute("successMessage", msg));
        return "index";
    }/*this returns us back to index.html */

    @GetMapping("/boardgame/{id}")
    public String boardgameDetail(@PathVariable int id, Model model) {
        Optional<Boardgamestable> boardgame = boardgamestableService.findById(id);
        if (boardgame.isPresent()) {
            List<BoardgameScore> scores = boardgameScoreService.getScoresByBoardgameId(id);
            int highestScore = boardgameScoreService.getHighestScore(id);
            double averageScore = boardgameScoreService.getAverageScore(id);

            model.addAttribute("boardgame", boardgame.get());
            model.addAttribute("scores", scores);
            model.addAttribute("highestScore", highestScore);
            model.addAttribute("averageScore", String.format("%.2f", averageScore));

            return "boardgame-detail";
        } else {
            return "error";
        }
    }/* this get method will get 1 specific board game */

    @PostMapping("/api/boardgames/{id}/score")
    public String addScore(@PathVariable int id, @RequestParam String playerName, @RequestParam int score) {
        Optional<Boardgamestable> boardgame = boardgamestableService.findById(id);
        if (boardgame.isPresent()) {
            BoardgameScore boardgameScore = new BoardgameScore(boardgame.get(), playerName, score);
            boardgameScoreService.save(boardgameScore);
        }
        return "redirect:/boardgame/" + id;
    }

}