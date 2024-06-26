package project.boardgames.model; /* this specifies which class BoardgameScores belongs to */

import jakarta.persistence.*;  /* this imports many annotations and classes as per below */
import java.time.LocalDateTime; /* represents date and time information */

@Entity /* marks this class as a JPA entity and will be mapped to a database */
@Table(name = "BoardgameScores") /* specifies the name of the database table being used */
public class BoardgameScore {
    
    @Id /*marks the primary key */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; /* specifies the primary key should be generated automatically */

    @ManyToOne /*specifies a one to many relationship */
    @JoinColumn(name = "boardgame_id", nullable = false) /*specifies the foreign key column that is used for the relationship */
    private Boardgamestable boardgame;

    @Column(name = "player_name", nullable = false)
    private String playerName;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "score_date", nullable = false)
    private LocalDateTime scoreDate;
    // the columns specifies mapping to columns in the database

    // No-argument constructor required by JPA
    public BoardgameScore() {
    }

    // Constructor with parameters initialises the fields in the entity 
    public BoardgameScore(Boardgamestable boardgame, String playerName, int score) {
        this.boardgame = boardgame;
        this.playerName = playerName;
        this.score = score;
        this.scoreDate = LocalDateTime.now();
    }

    // Getters and setters to get information and set information
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
