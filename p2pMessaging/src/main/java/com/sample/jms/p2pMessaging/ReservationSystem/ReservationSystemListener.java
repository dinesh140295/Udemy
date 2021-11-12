/**
 * 
 */
package com.sample.jms.p2pMessaging.ReservationSystem;

import java.io.Serializable;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
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
public class ReservationSystemListener implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext();) {
			
			InitialContext initialContext = new InitialContext();
			Queue replyQueue = (Queue) initialContext.lookup("queue/replyQueue");
			
			//type cast to model obj
			ObjectMessage objMsg = (ObjectMessage) msg;
			Passenger p = (Passenger) objMsg.getObject();
			
			//create Message
			MapMessage ackMessage = jmsContext.createMapMessage();
			ackMessage.setJMSCorrelationID(msg.getJMSMessageID());
			
			//validate Passenger
			if(p.getFirstName().equals("dinesh") && p.getLastName().equals("p")) {
				if(p.getId() == 123 && p.getEmail().equals("dineshp@gmail.com") && p.getPhoneNumber().equals("098756")) {
					ackMessage.setBooleanProperty("valid", true);
				}else {
					ackMessage.setBoolean("valid", false);
				}
			}
			
			//create producer
			JMSProducer jmsProducer = jmsContext.createProducer();
			jmsProducer.send(replyQueue, ackMessage);
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
