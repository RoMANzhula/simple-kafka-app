package org.romanzhula.dataanalyser.config;

import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class BeanConfig {

    @Bean
    public XML consumerXML() throws IOException {

        ClassPathResource resource = new ClassPathResource("kafka/consumer.xml");
        return new XMLDocument(resource.getInputStream());
    }

}
