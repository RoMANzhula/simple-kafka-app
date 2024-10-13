package org.romanzhula.dataanalyser.config;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class BeanConfig {

    @SneakyThrows //lombok's throwing exceptions to low layer
    @Bean
    public XML consumerXML() {
        return new XMLDocument(
                new File("src/main/resources/kafka/consumer.xml")
        );
    }

}
