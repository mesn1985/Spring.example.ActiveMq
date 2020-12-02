package lightmaze.spring.examples.SpringActiveMq.ActiveMqExample;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@SpringBootApplication
@EnableJms
public class ActiveMqExampleApplication {

	public static void main(String[] args) {

		JmsTemplate javaMessageServiceTemplate =
				SpringApplication
						.run(ActiveMqExampleApplication.class, args)
						.getBean(JmsTemplate.class);

		System.out.println("Sending messages");

		javaMessageServiceTemplate.convertAndSend(
				"IncomingMessageBox",
				new MessageDTO("Hej Michael")
		);
		javaMessageServiceTemplate.convertAndSend(
				"IncomingMessageBox",
				new MessageDTO("Det er internal broker :) ")
		);
	}

	@Bean
	public JmsListenerContainerFactory<?> listenerFactory(
			@Qualifier("jmsConnectionFactory") ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer
			){
		DefaultJmsListenerContainerFactory listenerFactory = new DefaultJmsListenerContainerFactory();
		configurer.configure(listenerFactory,connectionFactory);
		return listenerFactory;
	}
	@Bean
	public MessageConverter messageConverter(){
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

}
