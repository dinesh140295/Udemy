/**
 * 
 */
package com.sample.jms.pubsub.Alerts;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.sample.pubsub.creditcard.model.CreditCardInfo;

/**
 * @author reddyp
 *
 */
public class AlertsApp {

	/**
	 * @param args
	 * @throws NamingException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws NamingException, InterruptedException {
		
		InitialContext initialContext = new InitialContext();
		Topic topic = (Topic) initialContext.lookup("topic/ccTopic");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsConext = cf.createContext();) {
			
			//create durable consumers
			jmsConext.setClientID("Alerts App");
			JMSConsumer consumer = jmsConext.createDurableConsumer(topic, "subscription1");
			consumer.close();
			
			
			Thread.sleep(10000);
			
			JMSConsumer consumer1 = jmsConext.createDurableConsumer(topic, "subscription1");
			CreditCardInfo ccInfo = consumer1.receiveBody(CreditCardInfo.class);				
			System.out.println("Message from Alters App - " + ccInfo.getSwipeInfo() + " --------- " + ccInfo.getSwipedBy());
			consumer1.close();    
			
			jmsConext.unsubscribe("subscription1");
			
		}
		
		
		}

	}

