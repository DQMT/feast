package xyz.tincat.host.feast.mvc.config;

import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ Date       ：Created in 9:33 2019/11/25
 * @ Modified By：
 * @ Version:     0.1
 */
@Configuration
public class ErrorPageConfig {

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> containerCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                Set<ErrorPage> errorPages = new HashSet<>();
                errorPages.add(new ErrorPage(HttpStatus.BAD_REQUEST, "/400.html"));
                errorPages.add(new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html"));
                errorPages.add(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
                errorPages.add(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html"));
                factory.setErrorPages(errorPages);
            }
        };
    }
}