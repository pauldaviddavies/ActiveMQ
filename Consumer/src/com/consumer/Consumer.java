package com.consumer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;

public class Consumer {
    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final String QUEUE = "PDD_QUEUE1";

    public static void main(String args[]) {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE);
            MessageConsumer messageConsumer = session.createConsumer(destination);
            Message message = messageConsumer.receive();

            if(message instanceof Message) {
                TextMessage textMessage = (TextMessage)message;
                System.out.println("Received message: "+textMessage.getText());
            }
            else {
                System.out.println("Not instance of Text message");
            }

            connection.close();
        }
        catch (JMSException e) {
            System.out.println("JMS Exception: "+e.getMessage());
        }
    }
}
