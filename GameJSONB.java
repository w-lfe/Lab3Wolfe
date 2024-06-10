/** Project: LAb3
 * Purpose Details: To receive JSON
 * Course: IST 242
 * Author: Kadin
 * Date Developed: 6/8
 * Last Date Changed: 6/9
 * Rev:

 */





import com.rabbitmq.client.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GameJSONB {
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
            System.out.println("Waiting for game object...");

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String json = new String(delivery.getBody(), StandardCharsets.UTF_8);

                Game game = objectMapper.readValue(json, Game.class);
                System.out.println("Received game object:");
                System.out.println(game);
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

