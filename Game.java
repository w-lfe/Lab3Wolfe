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


    public Game() {}

    public Game(String name, int score, int level) {
        this.name = name;
        this.score = score;
        this.level = level;
    }

    /**
     *
     * @return
     */
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
    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", level=" + level +
                '}';
    }

    /**
     *
     * @return
     */
    public String toFlatFile() {
        return name + "," + score + "," + level;
    }

    /**
     *
     * @param flatFile
     * @return
     */
    public static Game fromFlatFile(String flatFile) {
        String[] parts = flatFile.split(",");
        return new Game(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }

    /**
     *
     * @param jsonString
     * @return
     * @throws IOException
     */
    public static Game fromString(String jsonString) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, Game.class);
    }
}
