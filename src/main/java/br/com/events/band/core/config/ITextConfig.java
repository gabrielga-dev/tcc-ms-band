package br.com.events.band.core.config;

import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.html.ImageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ITextConfig {

    @Bean
    public ImageProvider imageProvider() {
        return new Base64ImageProvider();
    }

    @Bean
    public CSSResolver cssResolver() {
        return XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
    }
}
