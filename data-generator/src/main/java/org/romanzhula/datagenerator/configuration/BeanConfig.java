package org.romanzhula.datagenerator.configuration;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class BeanConfig {

    @SneakyThrows //without exception handling in this layer
    @Bean
    public XML producerXML() {
        ClassPathResource resource = new ClassPathResource("kafka/producer.xml");
        return new XMLDocument(resource.getInputStream());
    }

}
