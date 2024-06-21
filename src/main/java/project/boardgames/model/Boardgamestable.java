package project.boardgames.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Boardgamestable")
public class Boardgamestable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Boardgame_name")
    private String name;

    @Column(name = "Boardgame_minPlayers")
    private int minPlayers;

    @Column(name = "Boardgame_maxPlayers")
    private int maxPlayers;

    @Column(name = "Boardgame_time")
    private int time;

    @Column(name = "Boardgame_difficulty")
    private String difficulty;

    @Column(name = "Boardgame_image")
    private String image;

    // No-argument constructor required by JPA
    public Boardgamestable() {
    }

    // Constructor with parameters
    public Boardgamestable(String name, int minPlayers, int maxPlayers, int time, String difficulty, String image) {
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.time = time;
        this.difficulty = difficulty;
        this.image = image;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}