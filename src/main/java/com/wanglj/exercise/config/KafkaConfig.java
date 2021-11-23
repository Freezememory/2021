package com.wanglj.exercise.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Wanglj
 * @Date: 2021/11/8 16:01
 * @Description :
 */
//@Component
public class KafkaConfig {
    /**
     * 生产者工厂
     */
    @Autowired
    ProducerFactory<Object, Object> pf;

    public void send() {
        // 动态创建三个生产者
        List<KafkaTemplate> kafkaTemplates = new ArrayList<>();
        String[] servers = {"192.168.60.4:9092", "192.168.60.5:9092", "192.168.60.6:9092"};
        for (String server : servers) {
            /*Map<String, Object> producerProps = new HashMap<>(pf.getConfigurationProperties());
            producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
            DefaultKafkaProducerFactory dpf = new DefaultKafkaProducerFactory<>(producerProps);*/
            Map<String, Object> producerProps = new HashMap<>();
            producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
            producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                    "org.apache.kafka.common.serialization.StringSerializer");
            producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                    "org.apache.kafka.common.serialization.StringSerializer");
            DefaultKafkaProducerFactory pf = new DefaultKafkaProducerFactory<>(producerProps);
            KafkaTemplate kafkaTemplate = new KafkaTemplate(pf, true);
            kafkaTemplates.add(kafkaTemplate);
        }
        //遍历三个生产者发送消息
        for (KafkaTemplate kafkaTemplate : kafkaTemplates) {
            kafkaTemplate.send("test", "welcome to hangge.com");
        }
    }
}
