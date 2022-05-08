package com.portal;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Portal API", version = "1.0.0", description = "Portal Service to get employee details", contact = @Contact(name = "Mohan", email = "mohankanuri@outlook.com")))
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MappingJackson2HttpMessageConverter messageConverter() {
        return new MappingJackson2HttpMessageConverter(new ObjectMapper());
    }

}
