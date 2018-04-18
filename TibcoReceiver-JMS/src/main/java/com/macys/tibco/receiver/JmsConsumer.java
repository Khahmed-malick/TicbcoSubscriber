/**
 * 
 */
package com.macys.tibco.receiver;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kahmed
 *
 */
@Component
public class JmsConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(JmsConsumer.class);


	@Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


	private static final String rtoTibcoTopic = "M.MKT.LTYEMS.OFFER.LTYAPP.POSOffersUpdate.Publish";
	@JmsListener(destination = rtoTibcoTopic,containerFactory = "myFactory")
	public void receiveRtoOfferPayLoad(final Message message) throws JMSException {
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		MessageHeaders headers =  message.getHeaders();
		LOG.info("Application : headers received : {}", headers);
		

		
		
	}

	

}
