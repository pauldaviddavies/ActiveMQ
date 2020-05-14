package com.producer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final String QUEUE = "PDD_QUEUE1";

    public static void main(String args[]) {
        try{
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE);
            MessageProducer messageProducer = session.createProducer(destination);

            TextMessage textMessage = session.createTextMessage("Pdd: Your message sent to the queue "+QUEUE);
            messageProducer.send(textMessage);
            System.out.println("Sent message: "+textMessage.getText().trim());
            connection.close();
        }
        catch (JMSException e){
            System.out.println("JMS Connection: "+e.getMessage());
        }
    }

}
