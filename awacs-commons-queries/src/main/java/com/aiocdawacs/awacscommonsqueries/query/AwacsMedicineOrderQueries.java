package com.aiocdawacs.awacscommonsqueries.query;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="sql.awacs")
public class AwacsMedicineOrderQueries {
	public String BULLET_ORDER_QUERY;
}