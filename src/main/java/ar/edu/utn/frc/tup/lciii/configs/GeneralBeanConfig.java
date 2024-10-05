package ar.edu.utn.frc.tup.lciii.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class GeneralBeanConfig {

    private static final int CONNECT_TIMEOUT = 50000;
    private static final int READ_TIMEOUT = 50000;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(CONNECT_TIMEOUT))
                .setReadTimeout(Duration.ofMillis(READ_TIMEOUT))
                .build();
    }




}
