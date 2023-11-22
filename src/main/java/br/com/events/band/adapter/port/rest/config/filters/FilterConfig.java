package br.com.events.band.adapter.port.rest.config.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class tells with interceptor will act at each request
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class FilterConfig implements WebMvcConfigurer {

    private final ApiKeyFilter apiKeyFilter;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(apiKeyFilter).addPathPatterns("/v1/**").
            excludePathPatterns(
                "/",
                "/v3/api-docs",
                "/v2/api-docs",
                "/v1/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**"
            );
    }
}
