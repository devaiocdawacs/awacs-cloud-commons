package com.aiocdawacs.awacscommonsqueries.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import com.aiocdawacs.awacscommonsqueries.service.DatabaseQueryService;

@Configuration
@Import(QueryProperties.class)
public class AwacsNativeQueriesModuleConfig {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired 
	DataSource datasource;


	@Bean
	public DatabaseQueryService getDatabaseQueryService() {
		return new DatabaseQueryService();
	}
}
