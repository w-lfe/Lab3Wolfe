/** Project: LAb3
 * Purpose Details: To send JSON
 * Course: IST 242
 * Author: Kadin
 * Date Developed: 6/8
 * Last Date Changed: 6/9
 * Rev:

 */

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GameJSONA {
    public static void main(String[] args) {
        try {
            Game game = new Game("Space Game", 100, 50, "OAKESCRUISER", "KAPTAIN KADIN", 100, 50);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(game);

            URL url = new URL("http://localhost:8080/game");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            conn.disconnect();
            System.out.println("Game object sent via Web Service: " + json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


