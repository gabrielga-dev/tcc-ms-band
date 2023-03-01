package br.com.events.band.application.config.feign;

import feign.Logger;
import feign.Logger.Level;
import org.springframework.context.annotation.Bean;

/**
 * This class sets the logging level of all feign clients
 *
 * @author Gabriel Guimarães de Almeida
 */
public class BaseFeignClientConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Level.HEADERS;
    }
}
