package com.aiocdawacs.awacscommonsqueries.query;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="sql.awacs")
public class AwacsMedicineOrderQueries {

    private String byBulletOrderSearchQuery;

	public String getByBulletOrderSearchQuery() {
		return byBulletOrderSearchQuery;
	}

	public void setByBulletOrderSearchQuery(String byBulletOrderSearchQuery) {
		this.byBulletOrderSearchQuery = byBulletOrderSearchQuery;
	}
}