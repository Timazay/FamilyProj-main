package com.example.familyproj.test.config;



/*@Configuration
@EnableJms*/
public class JmsConfig {

   /* @Bean
    public ArtemisConfigurationCustomizer customizer() {
        return new ArtemisConfigurationCustomizer() {
            @Override
            public void customize(org.apache.activemq.artemis.core.config.Configuration configuration) {
                try {
                    configuration.addAcceptorConfiguration("netty", "tcp://localhost:8161");
                } catch (Exception e) {
                    throw new RuntimeException("Failed to add netty transport acceptor to artemis instance", e);
                }
            }
        };
    }*/

    /*@Bean
    public MessageConverter messageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:8161");
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setMessageConverter(messageConverter());
        return jmsTemplate;
    }*/
}
