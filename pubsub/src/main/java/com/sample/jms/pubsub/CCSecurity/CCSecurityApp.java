/**
 * 
 */
package com.sample.jms.pubsub.CCSecurity;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.sample.pubsub.creditcard.model.CreditCardInfo;

/**
 * @author reddyp
 *
 */
public class CCSecurityApp {

	/**
	 * @param args
	 * @throws NamingException 
	 */
	public static void main(String[] args) throws NamingException {
		
		//create shared subscribers
		InitialContext initialContext = new InitialContext();
		Topic topic = (Topic) initialContext.lookup("topic/ccTopic");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsConext = cf.createContext();) {
			
			JMSConsumer ccConsumer1 = jmsConext.createSharedConsumer(topic, "ccSubscription");
			JMSConsumer ccConsumer2 = jmsConext.createSharedConsumer(topic, "ccSubscription");
			
			for(int i=0; i<10; i+=2) {			
				CreditCardInfo receivedMsg1 = ccConsumer1.receiveBody(CreditCardInfo.class);			
				if(receivedMsg1.getSwipedBy().equals("Dinesh Reddy P")) {
					System.out.println("Consumer - I: Card Swiped By Correct Person. No Security Breach");
				}else {
					System.out.println("Consumer - I: Card Swiped By Different Person. Security Breach. Call the Person Immediately");
				}
				
				CreditCardInfo receivedMsg2 = ccConsumer2.receiveBody(CreditCardInfo.class);
				if(receivedMsg2.getSwipedBy().equals("Dinesh Reddy P")) {
					System.out.println("Consumer - II: Card Swiped By Correct Person. No Security Breach");
				}else {
					System.out.println("Consumer - II: Card Swiped By Different Person. Security Breach. Call the Person Immediately");
				}
			}
			
		}
		

	}

}
