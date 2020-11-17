package com.aiocdawacs.awacscommonsqueries.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:query.properties")
public class AwacsNativeQueriesModuleConfig {

}
