package com.aiocdawacs.awacscommonsqueries.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:query.properties")
@ConfigurationProperties(prefix = "sql.awacs")
@Configuration
public class QueryProperties {

	private String BULLET_ORDER_QUERY;
}
