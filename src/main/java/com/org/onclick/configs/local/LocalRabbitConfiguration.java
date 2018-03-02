package com.org.onclick.configs.local;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author brijeshdhaker
 */
@Profile("local")
@Configuration
public class LocalRabbitConfiguration {

    public final static String replyQueueName = "my.reply.queue";
    public final static String requestQueueName = "my.request.queue";
    
    @Autowired
    private ConnectionFactory connectionFactory;
    
    @Bean
    Exchange exchange() {
        return new DirectExchange("message-exchange");
    }
    
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("test");
    }
    
    @Bean
    public Queue requestQueue() {
        return new Queue(requestQueueName);
    }
        
    @Bean
    public Queue replyQueue() {
        return new Queue(replyQueueName);
    }

        
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setExchange(exchange().getName());
        template.setRoutingKey("test");
        template.setReplyQueue(replyQueue());
        return template;
    }
    
    
    @Bean
    public SimpleMessageListenerContainer replyListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(replyQueue());
        container.setMessageListener(rabbitTemplate());
        return container;
    }
    
    @Bean
    public SimpleMessageListenerContainer serviceListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(requestQueue());
        container.setMessageListener(new MessageListenerAdapter(new MessageListener(),"receiveMessage"));
        return container;
    }
}
