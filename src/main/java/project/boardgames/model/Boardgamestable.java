package project.boardgames.model; /* specifies where the class belongs*/

import jakarta.persistence.Column; /* specifies the mapping of a column in the database to a field in the entity*/
import jakarta.persistence.Entity; /* marks the class as a jpa entity meaning it will be mapped to a database*/
import jakarta.persistence.GeneratedValue; /* this specifies that the primary key should be generated automatically by the database*/
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; /* this marks the primary key*/
import jakarta.persistence.Table; /* specifies the name of the database table to which this entity is mapped*/

@Entity /* indicates that this class is a JPA entity */
@Table(name = "Boardgamestable") /* specifies the name of the database being used */
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
    /* these relate to each column in the database table */


    // No-argument constructor required by JPA
    public Boardgamestable() {
    }

    // Constructor with parameters which initialises all fields of the entity
    public Boardgamestable(String name, int minPlayers, int maxPlayers, int time, String difficulty, String image) {
        this.name = name;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.time = time;
        this.difficulty = difficulty;
        this.image = image;
    }

    // Getters and setters for all fields 
    // The getters retrieve values
    // The setters set values
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