/** Project: LAb3
 * Purpose Details: To send RabbitMQ
 * Course: IST 242
 * Author: Kadin
 * Date Developed: 6/7
 * Last Date Changed: 6/9
 * Rev:

 */






import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GameA {
    private final static String QUEUE_NAME = "game_queue";

    /**
     *
     * @param argv
     * @throws Exception
     */
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            Game game = new Game("Space Game", 100, 50);

            String flatFileData = game.toFlatFile();
            channel.basicPublish("", QUEUE_NAME, null, flatFileData.getBytes(StandardCharsets.UTF_8));
            System.out.println("Game object sent via RabbitMQ");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}


