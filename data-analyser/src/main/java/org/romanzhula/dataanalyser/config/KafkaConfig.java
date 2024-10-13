package org.romanzhula.dataanalyser.config;

import com.jcabi.xml.XML;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    @Value("${topics}")
    private List<String> topics;

    private final XML settings;

    @Bean
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>(6);

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        properties.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                new TextXPath(this.settings, "//groupId").toString()
        );
        properties.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                new TextXPath(this.settings, "//keyDeserializer").toString()
        );
        properties.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                new TextXPath(this.settings, "//valueDeserializer").toString()
        );
        String toSpring = "spring.json.trusted.packages";
        properties.put(
                toSpring,
                new TextXPath(this.settings, "//trustedPackages").toString()
        );

        return properties;
    }

    @Bean
    public ReceiverOptions<String, Object> receiverOptions() {
        ReceiverOptions<String, Object> receiverOptions = ReceiverOptions.create(getProperties());

        return receiverOptions
                .subscription(topics)
                .addAssignListener(partitions -> System.out.println("Assigned: " + partitions))
                .addRevokeListener(partitions -> System.out.println("Revoked: " + partitions))
        ;
    }

    @Bean
    public KafkaReceiver<String, Object> receiver(ReceiverOptions<String, Object> receiverOptions) {
        return KafkaReceiver.create(receiverOptions);
    }

}
