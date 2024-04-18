package ru.gb;

import lombok.Data;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(value = "http.logging")
public class LoggingProperties {

    private Level logLevel = Level.DEBUG;
}
