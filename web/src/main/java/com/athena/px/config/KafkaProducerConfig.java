package com.athena.px.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;


/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/6/5 18:06
 */
@Configuration
@EnableKafka
public class KafkaProducerConfig {


    @Bean
    public NewTopic mailTopic(){
        return new NewTopic("mailTopic",3,(short) 2);
    }


    /*@Bean
    public Map<String,Object> producerConfigs(){
        Map<String,Object> props = new HashMap<>();
        return props;
    }*/
}
