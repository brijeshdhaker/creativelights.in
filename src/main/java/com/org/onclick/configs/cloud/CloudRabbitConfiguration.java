package com.org.onclick.configs.cloud;

import com.org.onclick.configs.MessageListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.common.AmqpServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author brijeshdhaker
 */
@Configuration
@Profile("cloud")
public class CloudRabbitConfiguration {

    public final static String queueName = "rabbit-mq";

    @Bean
    public ConnectionFactory connectionFactory() {
        CloudFactory cloudFactory = new CloudFactory();
        Cloud cloud = cloudFactory.getCloud();
        AmqpServiceInfo serviceInfo = (AmqpServiceInfo) cloud.getServiceInfo("mq-service");
        String serviceID = serviceInfo.getId();
        return cloud.getServiceConnector(serviceID, ConnectionFactory.class, null);
    }
    
    @Bean
    Exchange exchange() {
        return new TopicExchange("message-exchange");
    }
    
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("test");
    }
    
    @Bean
    public Queue requestQueue() {
        return new Queue(queueName);
    }
        
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setExchange(exchange().getName());
        template.setRoutingKey("test");
        template.setReplyQueue(requestQueue());
        return template;
    }
    
    @Bean
    public SimpleMessageListenerContainer serviceListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setQueues(requestQueue());
        container.setMessageListener(new MessageListenerAdapter(new MessageListener(),"receiveMessage"));
        return container;
    }

}
