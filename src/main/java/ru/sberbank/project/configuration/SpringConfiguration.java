package ru.sberbank.project.configuration;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.cloud.netflix.feign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import ru.sberbank.project.feign.ArticleFeignClient;
import ru.sberbank.project.feign.CommentFeignClient;
import ru.sberbank.project.feign.NewsFeignClient;

import static ru.sberbank.project.util.UrlUtil.ARTICLE_SERVICE_URL;
import static ru.sberbank.project.util.UrlUtil.NEWS_SERVICE_URL;

@Configuration
public class SpringConfiguration {

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

    @Bean
    CommentFeignClient commentFeignClient() {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.FULL)
                .target(CommentFeignClient.class, ARTICLE_SERVICE_URL);
    }

    @Bean
    NewsFeignClient newsFeignClient() {
        return Feign.builder()
                .contract(new SpringMvcContract())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.FULL)
                .target(NewsFeignClient.class, NEWS_SERVICE_URL);
    }
}
