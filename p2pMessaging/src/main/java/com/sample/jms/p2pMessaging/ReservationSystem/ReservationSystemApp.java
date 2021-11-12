/**
 * 
 */
package com.sample.jms.p2pMessaging.ReservationSystem;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

/**
 * @author reddyp
 *
 */
public class ReservationSystemApp {

	/**
	 * @param args
	 * @throws NamingException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws NamingException, InterruptedException {
		
		InitialContext initialContext = new InitialContext();
		Queue requestQueue = (Queue) initialContext.lookup("queue/requestQueue");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext();) {
			
			//create consumer
			JMSConsumer jmsConsumer = jmsContext.createConsumer(requestQueue);
			jmsConsumer.setMessageListener(new ReservationSystemListener());
			
			Thread.sleep(10000);
			
		}
		

	}

}
