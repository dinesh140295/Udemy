/**
 * 
 */
package com.sample.jms.p2pMessaging.FlightCheckIn;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.sample.jms.p2pMessaging.FlightCheckIn.Model.Passenger;

/**
 * @author reddyp
 *
 */
public class FlightCheckInApp {

	/**
	 * @param args
	 * @throws NamingException 
	 * @throws JMSException 
	 */
	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext initialContext = new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueue");
		Queue replyQueue = (Queue) initialContext.lookup("queue/replyQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext();) {
			
			//create Producer
			JMSProducer jmsProducer = jmsContext.createProducer();
			
			//create Obj Message
			ObjectMessage objectMessage = jmsContext.createObjectMessage();
			
			//create Passenger Model
			Passenger p = new Passenger();
			p.setId(123);
			p.setFirstName("dinesh");
			p.setLastName("p");
			p.setEmail("dineshp@gmail.com");
			p.setPhoneNumber("098756");
			
			//add object to message
			objectMessage.setObject(p);
			
			//add message id to Map
			Map<String, ObjectMessage> msgMap = new HashMap<>();
			
			//create tempQueue to receive ack
			//Queue replyQueue = jmsContext.createTemporaryQueue();
			
			//send the message
			jmsProducer.send(requestQueue, objectMessage);
			
			//add msgId to map
			msgMap.put(objectMessage.getJMSMessageID(), objectMessage);
			
			//receive message from replyQueue
			JMSConsumer jmsConsumer = jmsContext.createConsumer(replyQueue);
			MapMessage passMap = (MapMessage) jmsConsumer.receive(30000);
			
			//check the validity of Passenger
			System.out.println("Passenger Valid: " + passMap.getBooleanProperty("valid"));
			
			//validate the obj message sent
			ObjectMessage message = msgMap.get(passMap.getJMSCorrelationID());
			Passenger passenger = (Passenger) message.getObject();
			System.out.println("Passenger Details: " + passenger);
			
		}
		

	}

}
