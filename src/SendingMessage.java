
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

public class SendingMessage {
	private final static String QUEUE_NAME="hello";
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory= new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection=factory.newConnection();
		Channel channel=  connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message="Hello World! 2"+new Date(System.currentTimeMillis());
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println("[X] Sent "+message+" ");
		channel.close();
		connection.close();
	}
}
