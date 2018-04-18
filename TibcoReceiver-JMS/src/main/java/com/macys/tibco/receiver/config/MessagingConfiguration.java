/**
 * 
 */
package com.macys.tibco.receiver.config;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import com.tibco.tibjms.TibjmsConnectionFactory;

/**
 * @author Kahmed
 *
 */
@Configuration
public class MessagingConfiguration {

	@Value("${spring.ems.broker-url}")
	private String defaultBrokerUrl;

	@Value("${tibco.ems.ssl.identity.path}")
	private String tibcoIdentCert;
	
	@Value("${tibco.ems.ssl.trustedencoding.path}")
	private String tibcoCertEncodingPath;
	
	@Value("${tibco.ems.ssl.certificate.path}")
	private String tibcoCert;

	@Value("${tibco.ems.ssl.username}")
	private String userName;
	
	@Value("${tibco.ems.ssl.password}")
	private String pwd;

	@Bean
	public TibjmsConnectionFactory connectionFactory() throws JMSException{
		TibjmsConnectionFactory connectionFactory = new TibjmsConnectionFactory();
		connectionFactory.setServerUrl(defaultBrokerUrl);
		connectionFactory.setSSLEnableVerifyHost(false);
		connectionFactory.setSSLIdentity(tibcoIdentCert);
		connectionFactory.setSSLTrustedCertificate(tibcoCert,tibcoCertEncodingPath);
		connectionFactory.setUserName(userName);
		connectionFactory.setUserPassword(pwd);
		return connectionFactory;
	}

	@Bean("jmsQueueTemplate")
	public JmsTemplate jmsQueueTemplate() throws JMSException{
		JmsTemplate template = new JmsTemplate();

		template.setConnectionFactory(connectionFactory());
		//template.setDefaultDestinationName();
		template.setExplicitQosEnabled(false);

		return template;
	}

	@Bean("jmsTopicTemplate")
	public JmsTemplate jmsTopicTemplate() throws JMSException{
		JmsTemplate template = new JmsTemplate();

		template.setConnectionFactory(connectionFactory());
	//	template.setDefaultDestinationName(orderTopic);
		template.setExplicitQosEnabled(false);
		template.setPubSubDomain(true);

		return template;
	}

	@Bean
	public JmsMessagingTemplate jmsMessagingTemplate() throws JMSException{
		JmsMessagingTemplate jmsMessagingTemplate = new JmsMessagingTemplate();
		//jmsMessagingTemplate.setDefaultDestinationName(orderQueue);
		jmsMessagingTemplate.setJmsTemplate(jmsQueueTemplate());
		return jmsMessagingTemplate;
	}
}