package org.romanzhula.datagenerator.configuration;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class BeanConfig {

    @SneakyThrows //without exception handling in this layer
    @Bean
    public XML producerXML() {
        return new XMLDocument(new File("src/main/resources/kafka/producer.xml"));
    }

}
