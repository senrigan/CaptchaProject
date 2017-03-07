package Client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ClientRabbit {
    private final static String QUEUE_NAME="hello";
    
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("192.168.208.70");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("[*] waiting for message to exiit pres Ctrl C");
        
        Consumer consumer= new DefaultConsumer(channel){
            public void handleDelivery(String consumerTag,Envelope envelope , AMQP.BasicProperties properties, byte[] body)
                    throws UnsupportedEncodingException{
                String message= new String(body,"UTF-8");
                System.out.println("[X] received"+message+" ");
            }
        };
        channel.basicConsume(QUEUE_NAME, true,consumer);
    }

}