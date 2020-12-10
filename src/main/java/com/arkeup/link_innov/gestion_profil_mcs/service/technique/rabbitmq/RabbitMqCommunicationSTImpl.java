package com.arkeup.link_innov.gestion_profil_mcs.service.technique.rabbitmq;

import org.apache.qpid.jms.JmsQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.CustomExceptionListener;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.naming.Context;
import javax.naming.InitialContext;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

@Service
public class RabbitMqCommunicationSTImpl implements RabbitMqCommunicationST{
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Value("${spring.rabbitmq.host}")
	private String rabbitmqHost;
	
	@Value("${spring.rabbitmq.port}")
	private Integer rabbitmqPort;
	
	@Value("${spring.rabbitmq.username}")
	private String rabbitmqUsername;
	
	@Value("${spring.rabbitmq.password}")
	private String rabbitmqPassword;
	
    @Value("${spring.rabbitmq.notification.action.queue}")
    private String queueActionNotification;
    
    @Value("${connectionfactory.instanceUri}")
    private String connectionfactory;
	
	static final Logger LOG = LoggerFactory.getLogger(RabbitMqCommunicationSTImpl.class);

	@Override
	public Boolean sendMessageAction(Object obj) {
		
		String messageString = null;
		try {
			
			Properties props = new Properties();
	        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.qpid.jms.jndi.JmsInitialContextFactory");
	        props.setProperty("connectionfactory.instanceUri", connectionfactory);
	        
			messageString = objectMapper.writeValueAsString(obj);
            Context context = new InitialContext(props);

            ConnectionFactory factory = (ConnectionFactory) context.lookup("instanceUri");
            Destination queue = new JmsQueue(queueActionNotification) ;

            Connection connection = factory.createConnection(rabbitmqUsername, rabbitmqPassword);
            connection.setExceptionListener(new CustomExceptionListener());
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageProducer messageProducer = session.createProducer(queue);

            TextMessage message = session.createTextMessage(messageString);
            messageProducer.send(message, DeliveryMode.PERSISTENT, Message.DEFAULT_PRIORITY, Message.DEFAULT_TIME_TO_LIVE);

            connection.close();
        } catch (Exception exp) {
        	LOG.error("Caught exception, exiting : "+exp.getMessage());
        }

		return true;
	}

}
