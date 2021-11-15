/**
 * 
 */
package com.sample.jms.p2pMessaging.ReservationSystem;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.sample.jms.p2pMessaging.FlightCheckIn.Model.Passenger;

/**
 * @author reddyp
 *
 */
public class ReservationSystemApp {

	/**
	 * @param args
	 * @throws NamingException 
	 * @throws InterruptedException 
	 * @throws JMSException 
	 */
	public static void main(String[] args) throws NamingException, InterruptedException, JMSException {
		
		InitialContext initialContext = new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue/newQueue1");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext(JMSContext.SESSION_TRANSACTED);) {
			
			//JMSContext jmsContext = cf.createContext(JMSContext.CLIENT_ACKNOWLEDGE);
			
			//create consumer
			JMSConsumer consumer1 = jmsContext.createConsumer(requestQueue);
			//jmsConsumer.setMessageListener(new ReservationSystemListener());
			
			//Thread.sleep(10000);
			
			TextMessage textMessage1 = (TextMessage) consumer1.receive();
			
			System.out.println("Received text message1 : " + textMessage1.getText());
			
			//textMessage1.acknowledge();
			
			jmsContext.commit();
			
			//JMSConsumer consumer2 = jmsContext.createConsumer(requestQueue);
			
			TextMessage textMessage2 = (TextMessage) consumer1.receive();
			
			System.out.println("Received text message2 : " + textMessage2.getText());
			
			//textMessage2.acknowledge();
			
			
			//JMSConsumer consumer3 = jmsContext.createConsumer(requestQueue);
			
			TextMessage textMessage3 = (TextMessage) consumer1.receive();
			
			System.out.println("Received text message3 : " + textMessage3.getText());

		}
		

	}

}
