package br.com.events.band.application.config.feign;

import org.springframework.context.annotation.Bean;

import feign.Logger;

/**
 * This class sets the logging level of all feign clients
 *
 * @author Gabriel Guimarães de Almeida
 */
public class BaseFeignClientConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
