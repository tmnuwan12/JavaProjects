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

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;

/*
 * @author Thusitha Nuwan
 * @Date 08/07/2012
 */

public class ActiveMQ implements MessageListener{
	

	private static ActiveMQConnectionFactory connectionFactory;
	private static Object mutex = new Object();
	private static Connection connection;
	private static Session session;
	private static Queue destination;
	private static MessageProducer publisher;
	private static TextMessage textMessage;
	private static String BROKER_URL = "tcp://localhost:8089";
	private static String EMERGENCY_MESSAGE_QUEUE = "EMERGENCY";
	
public static void main(String[] args) throws Exception
{
	ActiveMQ server = new ActiveMQ();
	server.setUp();
	//Thread.sleep(60000);
	
	
	
	
}

//run embedded server
public static void startBrokerService() throws Exception
{
	BrokerService broker = new BrokerService();
	broker.setUseJmx(true);
	broker.addConnector(BROKER_URL);
	broker.start();
}

	
	private void setUp() throws JMSException, InterruptedException
	{
		connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
		
		connection = connectionFactory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createQueue(EMERGENCY_MESSAGE_QUEUE);	
		
		publisher = session.createProducer(destination);
		publisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		session.createConsumer(destination).setMessageListener(this);
		
		connection.start();
		
		textMessage = session.createTextMessage("This is Emergency Message From Client");
		
		publisher.send(textMessage);
		
		synchronized(mutex)
		{
			mutex.wait();
		}
		publisher.send(session.createTextMessage("END"));
		connection.stop();
		connection.close();
	
		
		
	}

	public void onMessage(Message message) {
		

			//System.out.print("Messages going through listener in serverside" + message.g + "\n");
		System.out.print("Invoking onMessage method on client" + "\n");
		if(message instanceof TextMessage)
		{
			TextMessage msg = (TextMessage) message;
			try {
				if (msg.getText().equals("RECEIVED"))
				{
					System.out.println("Got reply with 'RECEIVED' signal from worker");
					synchronized(mutex)
					{
						mutex.notify();
						
					}
				}
			
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
