/**
 * 
 */
package com.sample.pubsub.creditcard;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.sample.pubsub.creditcard.model.CreditCardInfo;

/**
 * @author reddyp
 *
 */
public class CreditCardApp {

	/**
	 * @param args
	 * @throws NamingException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws NamingException, InterruptedException {
		
		InitialContext initialContext = new InitialContext();
		Topic ccTpoic = (Topic) initialContext.lookup("topic/ccTopic");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext();) {
			
			CreditCardInfo ccInfo = new CreditCardInfo();
			ccInfo.setSwipedBy("Dinesh Reddy P");
			ccInfo.setSwipeInfo("Credit Card was swiped at Nandyal at time 05:20PM ");
			
			for(int i=0; i<10; i++) {
				JMSProducer producer = jmsContext.createProducer();
				producer.send(ccTpoic, ccInfo);
			}	
		}

	}

}
