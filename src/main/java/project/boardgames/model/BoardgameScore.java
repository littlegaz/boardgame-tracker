package project.boardgames.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BoardgameScores")
public class BoardgameScore {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "boardgame_id", nullable = false)
    private Boardgamestable boardgame;

    @Column(name = "player_name", nullable = false)
    private String playerName;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "score_date", nullable = false)
    private LocalDateTime scoreDate;

    // No-argument constructor required by JPA
    public BoardgameScore() {
    }

    // Constructor with parameters
    public BoardgameScore(Boardgamestable boardgame, String playerName, int score) {
        this.boardgame = boardgame;
        this.playerName = playerName;
        this.score = score;
        this.scoreDate = LocalDateTime.now();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boardgamestable getBoardgame() {
        return boardgame;
    }

    public void setBoardgame(Boardgamestable boardgame) {
        this.boardgame = boardgame;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(LocalDateTime scoreDate) {
        this.scoreDate = scoreDate;
    }
}
