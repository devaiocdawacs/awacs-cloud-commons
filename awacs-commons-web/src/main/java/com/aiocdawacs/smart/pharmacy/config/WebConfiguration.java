package com.aiocdawacs.smart.pharmacy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/actuator/info");
    }
    
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(Boolean.TRUE);
        loggingFilter.setIncludeQueryString(Boolean.TRUE);
        loggingFilter.setIncludePayload(Boolean.TRUE);
        loggingFilter.setIncludeHeaders(Boolean.TRUE);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }
}
