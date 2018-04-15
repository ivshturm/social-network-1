package ru.sberbank.project.configuration;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sberbank.project.repository.article.ArticleFeignClient;

@Configuration
public class Config {
    private static final String ARTICLE_SERVICE_URL = "http://localhost:8090";

    @Bean
    ArticleFeignClient articleFeignClient() {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.FULL)
                .target(ArticleFeignClient.class, ARTICLE_SERVICE_URL);
    }
}
