/** Project: LAb3
 * Purpose Details: To send JSON
 * Course: IST 242
 * Author: Kadin
 * Date Developed: 6/8
 * Last Date Changed: 6/9
 * Rev:

 */





import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class GameJSONA {
    private final static String QUEUE_NAME = "game_queue";

    /**
     *
     * @param argv
     * @throws Exception
     */
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        ObjectMapper objectMapper = new ObjectMapper();
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            Game game = new Game("Space Game", 100, 50);

            String json = objectMapper.writeValueAsString(game);
            channel.basicPublish("", QUEUE_NAME, null, json.getBytes());
            System.out.println("Game object sent via RabbitMQ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
