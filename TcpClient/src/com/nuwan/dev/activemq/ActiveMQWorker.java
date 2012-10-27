package com.nuwan.dev.activemq;



import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;


/**
 * @param args
 * @author Thusitha Nuwan
 * @Date 08/07/2012
 */
public class ActiveMQWorker implements MessageListener{

	private static Object mutex = new Object();
	private static ActiveMQConnectionFactory connectionFactory;
	private static Connection connection;
	private static Session session;
	private static Queue destination;
	private static MessageProducer publisher;
	private static String BROKER_URL = "tcp://localhost:8089";
	private static String EMERGENCY_MESSAGE_QUEUE = "EMERGENCY";
	
	public static void main(String[] args) throws JMSException, InterruptedException {
		// TODO Auto-generated method stub
		ActiveMQWorker listener = new ActiveMQWorker();
		listener.setup();
		
	}
	
	
	private void setup() throws JMSException
	{
		connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
		connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue(EMERGENCY_MESSAGE_QUEUE);
		publisher = session.createProducer(destination);
		publisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		session.createConsumer(destination).setMessageListener(this);
		connection.start();
		System.out.print("Worker is Waiting for messages");
	}


	public void onMessage(Message message) {
		
		System.out.print("Invoking onMessage method on the worker" + "\n");
			
		
		if(message instanceof TextMessage)
		{
			TextMessage msg = (TextMessage) message;
			try {
				if(msg.getText().equals("END"))
				{
					connection.close();
					System.out.print("Message 'END' received from client, Closing the connection Down" + "\n" );
					
					
					//connection.stop();
				}else
				{
					System.out.print("This Message recived from client>>" + msg.getText() + " sending reply back to say we have received the signal from the client" + "\n");
					publisher.send(session.createTextMessage("RECEIVED"));
				}
	
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
