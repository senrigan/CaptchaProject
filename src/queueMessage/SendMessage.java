package queueMessage;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class SendMessage {
	  private static final String TASK_QUEUE_NAME = "task_queue";

	public static void main(String[] args)  {
		try{
			
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			
			channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
			for(int i=0;i<100;i++){
				String message =i+" " + "Hola Mundo" + "'"+new Date(System.currentTimeMillis());
			
				
				channel.basicPublish( "", TASK_QUEUE_NAME,
						MessageProperties.PERSISTENT_TEXT_PLAIN,
						message.getBytes());
				System.out.println(" [x] Sent '"+i+" " + message + "'"+new Date(System.currentTimeMillis()));
			}
			
			
			channel.close();
			connection.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		//
//		ConnectionFactory factory= new ConnectionFactory();
//		factory.setHost("localhost");
//		Connection connection;
//		try {
//			connection = factory.newConnection();
//			Channel channel=  connection.createChannel();
//			boolean durable=true;
//			int prefetchCount =1;
//			channel.basicQos(prefetchCount);
//			channel.queueDeclare("hello", durable,false	, false, null);
//			for(int i=0;i<100;i++){
//				String message = "hola"+i+" "+new Date(System.currentTimeMillis());
//				channel.basicPublish("", "hello",MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
//				System.out.println(" [x] Sent '" + message + "'");
//				
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (TimeoutException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
	}
	
	private static String getMessage(String[] strings){
	    if (strings.length < 1)
	        return "Hello World!";
	    return joinStrings(strings, " ");
	}

	private static String joinStrings(String[] strings, String delimiter) {
	    int length = strings.length;
	    if (length == 0) return "";
	    StringBuilder words = new StringBuilder(strings[0]);
	    for (int i = 1; i < length; i++) {
	        words.append(delimiter).append(strings[i]);
	    }
	    return words.toString();
	}

}
