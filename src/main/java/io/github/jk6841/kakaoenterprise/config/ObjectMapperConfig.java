package io.github.jk6841.kakaoenterprise.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ObjectMapperConfig {
    @Primary
    @Bean
    ObjectMapper defaultObjectMapper() {
        return new ObjectMapper();
    }
}
