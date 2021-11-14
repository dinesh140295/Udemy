/**
 * 
 */
package com.sample.jms.messageFiltering.claimmanagment;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.sample.jms.messageFiltering.claimmanagment.model.Claim;

/**
 * @author reddyp
 *
 */
public class ClaimManagement {

	/**
	 * @param args
	 * @throws NamingException 
	 * @throws JMSException 
	 */
	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext initialContext = new InitialContext();
		Queue claimQueue1 = (Queue) initialContext.lookup("queue/claimQueue1");
		Queue claimQueue2 = (Queue) initialContext.lookup("queue/claimQueue2");
		Queue claimQueue3 = (Queue) initialContext.lookup("queue/claimQueue3");
		
		try(ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmsContext = cf.createContext();) {
			
			JMSProducer producer = jmsContext.createProducer();
			ObjectMessage objectMessage1 = jmsContext.createObjectMessage();
			ObjectMessage objectMessage2 = jmsContext.createObjectMessage();
			ObjectMessage objectMessage3 = jmsContext.createObjectMessage();
			
			//JMSConsumer consumer = jmsContext.createConsumer(claimQueue, "docType in ('Gynacologist', 'Nuerologist') or JMSPriority between 5 and 9");
			
			Claim claim1 = new Claim();
			claim1.setHospitalId(123);
			claim1.setClaimAmount(1000);
			claim1.setDoctorName("John");
			claim1.setDoctorType("Pediatrician");
			claim1.setInsuranceProvider("Blue Cross");
			objectMessage1.setObject(claim1);
			
			Claim claim2 = new Claim();
			claim2.setHospitalId(124);
			claim2.setClaimAmount(1000);
			claim2.setDoctorName("Hari");
			claim2.setDoctorType("Pediatrician");
			claim2.setInsuranceProvider("Blue Cross");
			objectMessage2.setObject(claim2);
			
			Claim claim3 = new Claim();
			claim3.setHospitalId(125);
			claim3.setClaimAmount(1000);
			claim3.setDoctorName("John");
			claim3.setDoctorType("Pediatrician");
			claim3.setInsuranceProvider("American");
			objectMessage3.setObject(claim3);
			
			//objectMessage.setIntProperty("hospitalId", 123);
			//objectMessage.setDoubleProperty("claimAmount", 1000);
			//objectMessage.setStringProperty("doctorName", "John");
			//objectMessage.setStringProperty("docType", "Pediatrician");
			
			//1st use case claim Amount should be in multiple of 10
			objectMessage1.setDoubleProperty("claimAmount", 1000);
			JMSConsumer consumer1 = jmsContext.createConsumer(claimQueue1, "claimAmount % 10 = 0");
			
			//2nd use case insuranceProvider should be blue cross or American and not united
			objectMessage2.setStringProperty("insProvider", "American");
			JMSConsumer consumer2 = jmsContext.createConsumer(claimQueue2, "insProvider IN ('Blue Cross', 'American') AND insProvider NOT IN ('UNITED')");
			
			//3rd use case doctor Name start with H
			objectMessage3.setStringProperty("docName", "Hari");
			JMSConsumer consumer3 = jmsContext.createConsumer(claimQueue3, "docName like 'H%'");

			producer.send(claimQueue1, objectMessage1);
			producer.send(claimQueue2, objectMessage2);
			producer.send(claimQueue3, objectMessage3);
		
			System.out.println("Message Received from Producer through filter from Queue1 : " + consumer1.receiveBody(Claim.class));
			System.out.println("Message Received from Producer through filter from Queue2 : " + consumer2.receiveBody(Claim.class));
			System.out.println("Message Received from Producer through filter from Queue3 : " + consumer3.receiveBody(Claim.class));
			
		}

	}

}
