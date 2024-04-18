package ru.gb;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
public class HttpLoggingAutoConfiguration {

    @Bean
    LoggingAspect loggingAspect(){
        return new LoggingAspect();
    }

//    @Bean
////    @ConditionalOnProperty(value = "http.logging.enabled", havingValue = "true")
//    LoggerFilter loggerFilter(LoggingProperties loggingProperties){
//        return new LoggerFilter(loggingProperties);
//    }

}
