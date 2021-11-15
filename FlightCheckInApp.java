/**
 * 
 */
package com.sample.jms.p2pMessaging.FlightCheckIn;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

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
		Queue requestQueue = (Queue) initialContext.lookup("queue/newQueue1");
		//Queue replyQueue = (Queue) initialContext.lookup("queue/replyQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext(JMSContext.SESSION_TRANSACTED);) {
			
			//create Producer
			JMSProducer jmsProducer = jmsContext.createProducer();
			
			//create Obj Message
			/*ObjectMessage objectMessage = jmsContext.createObjectMessage();
			ObjectMessage objectMessage1 = jmsContext.createObjectMessage();
			ObjectMessage objectMessage2 = jmsContext.createObjectMessage();
			
			//create Passenger Model
			Passenger p = new Passenger();
			p.setId(123);
			p.setFirstName("dinesh");
			p.setLastName("p");
			p.setEmail("dineshp@gmail.com");
			p.setPhoneNumber("098756");
			
			Passenger p1 = new Passenger();
			p1.setId(124);
			p1.setFirstName("ravi");
			p1.setLastName("p");
			p1.setEmail("ravip@gmail.com");
			p1.setPhoneNumber("098777");
			
			Passenger p2 = new Passenger();
			p2.setId(125);
			p2.setFirstName("shyam");
			p2.setLastName("p");
			p2.setEmail("shyamp@gmail.com");
			p2.setPhoneNumber("098700");
			
			//add object to message
			objectMessage.setObject(p);
			objectMessage1.setObject(p1);
			objectMessage2.setObject(p2);
			
			//add message id to Map
			//Map<String, ObjectMessage> msgMap = new HashMap<>();
			
			//create tempQueue to receive ack
			//Queue replyQueue = jmsContext.createTemporaryQueue();*/
			
			//send the message
			TextMessage textMessage1 = jmsContext.createTextMessage("Message 1");
			jmsProducer.send(requestQueue, textMessage1);
			
			TextMessage textMessage2 = jmsContext.createTextMessage("Message 2");
			jmsProducer.send(requestQueue, textMessage2);
			
			jmsContext.commit();
			
			TextMessage textMessage3 = jmsContext.createTextMessage("Message 3");
			jmsProducer.send(requestQueue, textMessage3);
			//jmsContext.commit();
			
			//add msgId to map
			//msgMap.put(objectMessage.getJMSMessageID(), objectMessage);
			
			//receive message from replyQueue
			//JMSConsumer jmsConsumer = jmsContext.createConsumer(replyQueue);
			//MapMessage passMap = (MapMessage) jmsConsumer.receive(30000);
			
			//check the validity of Passenger
			//System.out.println("Passenger Valid: " + passMap.getBooleanProperty("valid"));
			
			//validate the obj message sent
			//ObjectMessage message = msgMap.get(passMap.getJMSCorrelationID());
			//Passenger passenger = (Passenger) message.getObject();
			//System.out.println("Passenger Details: " + passenger);
			
		}
		

	}

}
