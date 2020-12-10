package com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomExceptionListener  implements ExceptionListener{
	
	static final Logger LOG = LoggerFactory.getLogger(CustomExceptionListener.class);

	@Override
	public void onException(JMSException exception) {
		 LOG.error("Connection ExceptionListener fired, exiting :"+exception.getMessage());
	}

}
