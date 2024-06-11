/** Project: Lab3
 * Purpose Details: To dumb down the game example to get concept
 * Course: IST 242
 * Author: Kadin
 * Date Developed: Lab1?
 * Last Date Changed: 6/9
 * Rev: bare bones code to eliminate risk for error

 */

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;

public class Game implements Serializable {
    private String name;
    private int score;
    private int level;
    private String shipType;
    private String playerName;
    private int health;
    private int attack;

    /**
     *
     */
    public Game() {}

    /**
     *
     * @param name
     * @param score
     * @param level
     * @param shipType
     * @param playerName
     * @param health
     * @param attack
     */
    public Game(String name, int score, int level, String shipType, String playerName, int health, int attack) {
        this.name = name;
        this.score = score;
        this.level = level;
        this.shipType = shipType;
        this.playerName = playerName;
        this.health = health;
        this.attack = attack;
    }

    /**
     *
     * @return
     */
    // Getters and setters
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     *
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     *
     * @return
     */
    public String getShipType() {
        return shipType;
    }

    /**
     *
     * @param shipType
     */
    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    /**
     *
     * @return
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     *
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     *
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     *
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     *
     * @return
     */
    public int getAttack() {
        return attack;
    }

    /**
     *
     * @param attack
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", level=" + level +
                ", shipType='" + shipType + '\'' +
                ", playerName='" + playerName + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                '}';
    }

    /**
     *
     * @return
     */
    // Method to convert the game object to a flat file string representation
    public String toFlatFile() {
        return name + "," + score + "," + level + "," + shipType + "," + playerName + "," + health + "," + attack;
    }

    /**
     *
     * @param flatFile
     * @return
     */
    // Static method to create a game object from a flat file string representation
    public static Game fromFlatFile(String flatFile) {
        String[] parts = flatFile.split(",");
        return new Game(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
                parts[3], parts[4], Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
    }

    /**
     *
     * @param jsonString
     * @return
     * @throws IOException
     */
    // Static method to create a game object from a JSON string representation
    public static Game fromString(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, Game.class);
    }

    /**
     *
     * @return
     * @throws IOException
     */
    // Method to convert the game object to a JSON string representation
    public String toJSONString() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
}

