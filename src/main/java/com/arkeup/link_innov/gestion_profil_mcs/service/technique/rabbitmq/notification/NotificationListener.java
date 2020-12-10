//package com.arkeup.link_innov.gestion_profil_mcs.service.technique.rabbitmq.notification;
//
//import java.io.IOException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Component;
//
//import com.arkeup.link_innov.donnee.LinkInnovCommunication;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.rabbitmq.client.Channel;
//
//
//@Component
//public class NotificationListener {
//	
//	static final Logger LOG = LoggerFactory.getLogger(NotificationListener.class);
//	
//	@Autowired
//	private ObjectMapper objectMapper;
//  
//  @RabbitListener(queues = "#{'${spring.rabbitmq.notification.action.queue}'}")
//  public void receive( byte[] bytes , Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag){
//  	
//      try {
//      	String message  = new String(bytes, "UTF-8");
//      	LOG.info(message);
//      	LinkInnovCommunication linkInnovCommunication = objectMapper.readValue(message, LinkInnovCommunication.class);
//      	String log = "The tag :" + tag +" LinkInnovCommunication Received: " + linkInnovCommunication;
//      	LOG.info(log);
//			channel.basicAck(tag, false);
//		} catch (IOException e) {
//			String error = "Error : "+ e.getMessage();
//			LOG.error(error);
//		}
//  }
//	
//}
